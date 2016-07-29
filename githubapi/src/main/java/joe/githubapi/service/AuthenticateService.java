package joe.githubapi.service;

import joe.githubapi.model.authentication.AuthenticationInfo;
import joe.githubapi.model.authentication.AuthorizationParam;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    Observable<AuthenticationInfo> login(@Header("Authorization") String authorization, @Header("X-GitHub-OTP") String code, @Body AuthorizationParam info);

    @GET("/applications/{client_id}/tokens/{access_token}")
    Observable<ResponseBody> checkAuthorization(@Header("Authorization") String authorization, @Path("client_id") String client_id, @Path("access_token") String token);
}