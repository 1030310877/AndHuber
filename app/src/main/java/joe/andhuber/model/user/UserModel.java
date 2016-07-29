package joe.andhuber.model.user;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Random;

import joe.andhuber.config.UserConfig;
import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.ErrorInfo;
import joe.githubapi.model.authentication.AuthenticationInfo;
import joe.githubapi.model.authentication.AuthorizationParam;
import joe.githubapi.model.user.UserInfo;
import joe.githubapi.rx.HttpSubscriber;
import okhttp3.Headers;
import okhttp3.ResponseBody;
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
        ArrayList<String> scopes = new ArrayList<>();
        scopes.add("user");
        scopes.add("repo");
        scopes.add("delete_repo");
        scopes.add("public_repo");
        AuthorizationParam info = new AuthorizationParam();
        info.setClient_id(GitHubApi.CLIENT_ID);
        info.setClient_secret(GitHubApi.CLIENT_SECRET);
        info.setNote("AndHuber--Android Github Client");
        info.setFingerprint(generateRadonString(12));
        info.setScopes(scopes);
        GitHubApi.getAuthenticateApi()
                .login(user.getUserName(), user.getPassWord(), code, info)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AuthenticationInfo>() {
                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            Headers headers = ((HttpException) e).response().headers();
                            String value = headers.get("X-GitHub-OTP");
                            if (TextUtils.isEmpty(value)) {
                                ErrorInfo errorInfo = new ErrorInfo();
                                errorInfo.setMessage("value is null");
                                if (callBack != null) {
                                    callBack.loginFailed(errorInfo);
                                    return;
                                }
                            }
                            if (value.contains("required")) {
                                ErrorInfo errorInfo = new ErrorInfo();
                                errorInfo.setMessage("required");
                                if (callBack != null) {
                                    callBack.loginFailed(errorInfo);
                                }
                            }
                        } else {
                            ErrorInfo errorInfo = new ErrorInfo();
                            errorInfo.setMessage(e.toString());
                            if (callBack != null) {
                                callBack.loginFailed(errorInfo);
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (callBack != null) {
                            callBack.loginSuccess();
                        }
                    }

                    @Override
                    public void onNext(AuthenticationInfo authenticationResult) {
                        UserConfig.getInstance().setToken(authenticationResult.getToken());
                    }
                });
    }

    @Override
    public void logout() {
        UserConfig.nowUser = null;
        UserConfig.getInstance().removeToken();
    }

    @Override
    public void getUserInfo(String username, String token, GetUserInfoCallBack callBack) {
        GitHubApi.getUserApi().getUserInfo(username, token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onHttpError(ErrorInfo info) {
                        if (callBack != null) {
                            callBack.getFailed(info);
                        }
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        if (callBack != null) {
                            callBack.getSuccessfully(userInfo);
                        }
                    }
                });
    }

    @Override
    public void checkAuthorization(String token, CheckCallBack callBack) {
        GitHubApi.getAuthenticateApi().checkAuthorization(GitHubApi.CLIENT_ID, GitHubApi.CLIENT_SECRET, token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null) {
                            callBack.checkFailed();
                        }
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (callBack != null) {
                            callBack.checkSuccess();
                        }
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