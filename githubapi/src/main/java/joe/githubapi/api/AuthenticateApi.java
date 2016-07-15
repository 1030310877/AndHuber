package joe.githubapi.api;

import android.util.Base64;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.authentication.AuthenticationResult;
import joe.githubapi.model.authentication.AuthorizationInfo;
import joe.githubapi.service.AuthenticateService;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/4.
 */
public class AuthenticateApi {

    private AuthenticateService createService() {
        return GitHubApi.createService(AuthenticateService.class);
    }

    public Observable<AuthenticationResult> login(String username, String password, String code, AuthorizationInfo info) {
        String credentials = username + ":" + password;
        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT);
        basic = basic.trim();
        return createService().login(basic, code, info);
    }
}
