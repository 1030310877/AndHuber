package joe.andhuber.search.presenter;

import java.util.List;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.search.ISearchCallBack;
import joe.andhuber.model.search.SearchModel;
import joe.andhuber.model.search.SearchUsersParam;
import joe.andhuber.search.view.SearchUsersView;
import joe.githubapi.model.search.SearchUserResult;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/21.
 */
public class SearchUsersPresenterImpl implements SearchUsersPresenter {

    private SearchUsersView view;
    private SearchModel model;

    public SearchUsersPresenterImpl(SearchUsersView view) {
        this.view = view;
        model = new SearchModel();
    }

    @Override
    public void searchUsers(SearchUsersParam param) {
        model.searchUsers(param, new ISearchCallBack<SearchUserResult>() {
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
            }

            @Override
            public void onFailed(String errorInfo) {
                view.loadFinished(HuberApplication.getInstance().getResources().getString(R.string.load_failed));
            }
        });
    }

    @Override
    public void searchUsers(String q, String language, String sort, int page, String access_token) {
        SearchUsersParam param = new SearchUsersParam();
        param.setQ(q + "+language:" + language);
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
        param.setAccess_token(UserConfig.getInstance().getToken());
        searchUsers(param);
    }
}
