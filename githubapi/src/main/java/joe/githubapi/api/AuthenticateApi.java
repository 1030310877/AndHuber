package joe.githubapi.api;

import android.util.Base64;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.authentication.AuthenticationInfo;
import joe.githubapi.model.authentication.AuthorizationParam;
import joe.githubapi.service.AuthenticateService;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/4.
 */
public class AuthenticateApi {

    private AuthenticateService createService() {
        return GitHubApi.createService(AuthenticateService.class);
    }

    public Observable<AuthenticationInfo> login(String username, String password, String code, AuthorizationParam info) {
        String credentials = username + ":" + password;
        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        basic = basic.trim();
        return createService().login(basic, code, info);
    }

    public Observable<ResponseBody> checkAuthorization(String client_id, String client_secret, String token) {
        String credentials = client_id + ":" + client_secret;
        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        basic = basic.trim();
        return createService().checkAuthorization(basic, client_id, token);
    }
}
