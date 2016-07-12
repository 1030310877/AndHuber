package joe.githubapi.service;

import java.util.List;
import java.util.Map;

import joe.githubapi.model.event.EventInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public interface ActivityService {

    @GET("/user/starred")
    Observable<List<RepositoryInfo>> getNowUsersStars(@QueryMap Map<String, String> params);

    @GET("/users/{user}/received_events")
    Observable<List<EventInfo>> getUsersReceivedEvents(@Path("user") String userName, @QueryMap Map<String, String> params);
}