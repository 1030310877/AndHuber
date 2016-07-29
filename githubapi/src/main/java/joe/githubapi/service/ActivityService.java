package joe.githubapi.service;

import java.util.List;
import java.util.Map;

import joe.githubapi.model.activity.SubscriptionInfo;
import joe.githubapi.model.activity.SubscriptionParam;
import joe.githubapi.model.event.EventInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public interface ActivityService {

    @GET("/user/starred")
    Observable<List<RepositoryInfo>> getNowUsersStars(@QueryMap Map<String, String> params);

    @GET("/users/{username}/starred")
    Observable<List<RepositoryInfo>> getUserStars(@Path("username") String username, @QueryMap Map<String, String> params);

    @GET("/users/{username}/received_events")
    Observable<List<EventInfo>> getUsersReceivedEvents(@Path("username") String username, @QueryMap Map<String, String> params);

    @GET("/user/starred/{owner}/{repo}")
    Observable<ResponseBody> isStarringRepo(@Path("owner") String owner, @Path("repo") String repo, @Query("access_token") String token);

    @PUT("/user/starred/{owner}/{repo}")
    @Headers({
            "Content-Length: 0"
    })
    Observable<ResponseBody> starRepository(@Path("owner") String owner, @Path("repo") String repo, @Query("access_token") String token);

    @DELETE("/user/starred/{owner}/{repo}")
    Observable<ResponseBody> unStarRepository(@Path("owner") String owner, @Path("repo") String repo, @Query("access_token") String token);

    @GET("/repos/{owner}/{repo}/subscription")
    Observable<SubscriptionInfo> getASubscription(@Path("owner") String owner, @Path("repo") String repo, @Query("access_token") String token);

    @PUT("/repos/{owner}/{repo}/subscription")
    Observable<SubscriptionInfo> setASubscription(@Path("owner") String owner, @Path("repo") String repo, @Body SubscriptionParam body, @Query("access_token") String token);

    @DELETE("/repos/{owner}/{repo}/subscription")
    @Headers({
            "Content-Length: 0"
    })
    Observable<ResponseBody> deleteASubscription(@Path("owner") String owner, @Path("repo") String repo, @Query("access_token") String token);
}