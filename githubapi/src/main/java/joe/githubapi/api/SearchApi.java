package joe.githubapi.api;

import java.util.Map;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.search.SearchRepoResult;
import joe.githubapi.model.search.SearchUserResult;
import joe.githubapi.service.SearchService;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class SearchApi {

    public SearchApi() {
    }

    private SearchService createService() {
        return GitHubApi.createService(SearchService.class);
    }

    public Observable<SearchUserResult> searchUser(Map<String, String> params) {
        return createService().searchUsers(params);
    }

    public Observable<SearchRepoResult> searchRepository(Map<String, String> params) {
        return createService().searchRepositories(params);
    }
}