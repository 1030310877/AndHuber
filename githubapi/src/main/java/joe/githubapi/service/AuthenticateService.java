package joe.githubapi.service;

import joe.githubapi.model.authentication.AuthenticationResult;
import joe.githubapi.model.authentication.AuthorizationInfo;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/1.
 */
public interface AuthenticateService {

    @POST("/authorizations")
    @Headers({
            "Accept: application/vnd.github.v3.full+json"
    })
    Observable<AuthenticationResult> login(@Header("Authorization") String authorization, @Header("X-GitHub-OTP") String code, @Body AuthorizationInfo info);
}
