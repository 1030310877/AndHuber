package joe.andhuber.search.presenter;

import joe.andhuber.model.search.SearchUsersParam;

/**
 * Description
 * Created by chenqiao on 2016/7/21.
 */
public interface SearchUsersPresenter {
    void searchUsers(SearchUsersParam param);

    void searchUsers(String q, String language, String sort, int page, String access_token);
}
