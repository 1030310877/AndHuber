package joe.andhuber.search.presenter;

import java.util.List;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.search.ISearchCallBack;
import joe.andhuber.model.search.SearchModel;
import joe.andhuber.model.search.SearchParam;
import joe.andhuber.search.view.SearchReposView;
import joe.andhuber.utils.ToastUtil;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.model.search.SearchRepoResult;
import rx.Subscription;

/**
 * Description
 * Created by chenqiao on 2016/7/26.
 */
public class SearchReposPresenterImpl implements SearchReposPresenter {

    private SearchReposView view;
    private SearchModel model;
    private Subscription searchSubscription;

    public SearchReposPresenterImpl(SearchReposView view) {
        this.view = view;
        model = new SearchModel();
    }

    @Override
    public void searchRepositories(SearchParam param) {
        param.setAccess_token(UserConfig.getInstance().getToken());
        if (searchSubscription != null) {
            searchSubscription.unsubscribe();
            searchSubscription = null;
        }
        view.showWaitDialog();
        searchSubscription = model.searchRepositories(param, new ISearchCallBack<SearchRepoResult>() {
            @Override
            public void onSuccessfully(SearchRepoResult result) {
                List<RepositoryInfo> repositories = result.getItems();
                if (repositories != null) {
                    if (repositories.size() == 0) {
                        view.loadFinished(HuberApplication.getInstance().getResources().getString(R.string.load_to_bottom));
                    } else {
                        view.showRepositories(repositories);
                        view.setPage(param.getPage());
                        view.loadFinished(HuberApplication.getInstance().getResources().getString(R.string.load_success));
                    }
                }
                view.setTotal(result.getTotal_count());
                view.refreshFinish();
                view.dismissWaitDialog();
            }

            @Override
            public void onFailed(String errorInfo) {
                view.dismissWaitDialog();
                view.loadFinished(HuberApplication.getInstance().getResources().getString(R.string.load_failed));
                view.refreshFinish();
                ToastUtil.show(HuberApplication.getInstance(), errorInfo);
            }
        });
    }

    @Override
    public void searchRepositories(String q, String language, String sort, int page) {
        SearchParam param = new SearchParam();
        param.setQ(q + " language:" + language);
        param.setPage(page);
        if (sort.contains("Fewest") || sort.contains("Least")) {
            param.setOrder("asc");
        } else {
            param.setOrder("desc");
        }
        if (sort.contains("stars")) {
            param.setSort("stars");
        } else if (sort.contains("forks")) {
            param.setSort("forks");
        } else if (sort.contains("updated")) {
            param.setSort("updated");
        }
        searchRepositories(param);
    }
}
