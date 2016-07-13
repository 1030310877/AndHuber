package joe.andhuber.model.user;

import java.util.Random;

import joe.andhuber.config.UserConfig;
import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.ErrorInfo;
import joe.githubapi.model.authentication.AuthenticationResult;
import joe.githubapi.model.authentication.AuthorizationInfo;
import joe.githubapi.model.user.UserInfo;
import joe.githubapi.rx.HttpSubscriber;
import okhttp3.Headers;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public class UserModel implements IUser {
    @Override
    public void login(User user, String code, LoginCallBack callBack) {
        AuthorizationInfo info = new AuthorizationInfo();
        info.setClient_id(GitHubApi.CLIENT_ID);
        info.setClient_secret(GitHubApi.CLIENT_SECRET);
        info.setNote("AndHuber--Android Github Client");
        info.setFingerprint(generateRadonString(12));
        GitHubApi.getAuthenticateApi()
                .login(user.getUserName(), user.getPassWord(), code, info)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AuthenticationResult>() {
                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            Headers headers = ((HttpException) e).response().headers();
                            String value = headers.get("X-GitHub-OTP");
                            if (value.contains("required")) {
                                ErrorInfo errorInfo = new ErrorInfo();
                                errorInfo.setMessage("required");
                                callBack.loginFailed(errorInfo);
                            }
                        } else {
                            ErrorInfo errorInfo = new ErrorInfo();
                            errorInfo.setMessage(e.toString());
                            callBack.loginFailed(errorInfo);
                        }
                    }

                    @Override
                    public void onCompleted() {
                        callBack.loginSuccess();
                    }

                    @Override
                    public void onNext(AuthenticationResult authenticationResult) {
                        UserConfig.getInstance().setToken(authenticationResult.getToken());
                    }
                });
    }

    @Override
    public void getUserInfo(String username, String token, GetUserInfoCallBack callBack) {
        GitHubApi.getUserApi().getUserInfo(username, token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                        callBack.getSuccessfully();
                    }

                    @Override
                    public void onHttpError(ErrorInfo info) {
                        callBack.getFailed(info);
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        UserConfig.nowUser = userInfo;
                    }
                });
    }

    private String generateRadonString(int length) {
        Random random = new Random(System.currentTimeMillis());
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}