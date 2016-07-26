package joe.andhuber.search.presenter;

import joe.andhuber.model.search.SearchParam;

/**
 * Description
 * Created by chenqiao on 2016/7/26.
 */
public interface SearchReposPresenter {

    void searchRepositories(SearchParam param);

    void searchRepositories(String q, String language, String sort, int page);
}
