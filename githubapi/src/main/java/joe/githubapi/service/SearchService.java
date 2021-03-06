package joe.githubapi.service;

import java.util.Map;

import joe.githubapi.model.search.SearchRepoResult;
import joe.githubapi.model.search.SearchUserResult;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public interface SearchService {

    @GET("/search/repositories")
    @Headers({
            "Accept:application/vnd.github.v3.text-match+json"
    })
    Observable<SearchRepoResult> searchRepositories(@QueryMap Map<String, String> params);

    @GET("/search/users")
    @Headers({
            "Accept:application/vnd.github.v3.text-match+json"
    })
    Observable<SearchUserResult> searchUsers(@QueryMap Map<String, String> params);
}