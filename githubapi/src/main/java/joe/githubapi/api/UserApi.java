package joe.githubapi.api;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.user.UserInfo;
import joe.githubapi.model.user.UserParam;
import joe.githubapi.service.UserService;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/5.
 */
public class UserApi {

    public UserApi() {

    }

    private UserService createService() {
        return GitHubApi.createService(UserService.class);
    }

    public Observable<UserInfo> getNowUserInfo(String token) {
        return createService().getNowUserInfo(token);
    }

    public Observable<UserInfo> getUserInfo(String username, String token) {
        return createService().getUserInfo(username, token);
    }

    public Observable<UserInfo> updateUser(UserParam body, String token) {
        return createService().updateUserInfo(body, token);
    }
}
