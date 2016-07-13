package joe.githubapi.service;

import joe.githubapi.model.user.UserInfo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/5.
 */
public interface UserService {
    @GET("/user")
    Observable<UserInfo> getNowUserInfo(@Query("access_token") String token);

    @GET("/users/{username}")
    Observable<UserInfo> getUserInfo(@Path("username") String username, @Query("access_token") String token);
}
