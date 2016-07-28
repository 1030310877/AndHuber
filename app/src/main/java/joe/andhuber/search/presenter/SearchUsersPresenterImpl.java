package joe.andhuber.search.presenter;

import java.util.List;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.search.ISearchCallBack;
import joe.andhuber.model.search.SearchModel;
import joe.andhuber.model.search.SearchParam;
import joe.andhuber.search.view.SearchUsersView;
import joe.andhuber.utils.ToastUtil;
import joe.githubapi.model.search.SearchUserResult;
import joe.githubapi.model.user.UserInfo;
import rx.Subscription;

/**
 * Description
 * Created by chenqiao on 2016/7/21.
 */
public class SearchUsersPresenterImpl implements SearchUsersPresenter {

    private SearchUsersView view;
    private SearchModel model;
    private Subscription searchSubscription;

    public SearchUsersPresenterImpl(SearchUsersView view) {
        this.view = view;
        model = new SearchModel();
    }

    @Override
    public void searchUsers(SearchParam param) {
        param.setAccess_token(UserConfig.getInstance().getToken());
        if (searchSubscription != null) {
            searchSubscription.unsubscribe();
            searchSubscription = null;
        }
        view.showWaitDialog();
        searchSubscription = model.searchUsers(param, new ISearchCallBack<SearchUserResult>() {
            @Override
            public void onSuccessfully(SearchUserResult result) {
                List<UserInfo> users = result.getItems();
                if (users != null) {
                    if (users.size() == 0) {
                        view.loadFinished(HuberApplication.getInstance().getResources().getString(R.string.load_to_bottom));
                    } else {
                        view.showUsers(users);
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
    public void searchUsers(String q, String language, String sort, int page) {
        SearchParam param = new SearchParam();
        param.setQ(q + " language:" + language);
        param.setPage(page);
        if (sort.contains("Fewest") || sort.contains("Least")) {
            param.setOrder("asc");
        } else {
            param.setOrder("desc");
        }
        if (sort.contains("followers")) {
            param.setSort("followers");
        } else if (sort.contains("repositories")) {
            param.setSort("repositories");
        } else if (sort.contains("joined")) {
            param.setSort("joined");
        }
        searchUsers(param);
    }
}
