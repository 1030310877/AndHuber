package joe.githubapi.api;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.user.UserInfo;
import joe.githubapi.service.UserService;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/5.
 */
public class UserApi {

    public UserApi() {

    }

    public Observable<UserInfo> getNowUserInfo(String token) {
        return GitHubApi.createService(UserService.class).getNowUserInfo(token);
    }
}
