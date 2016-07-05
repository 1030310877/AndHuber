package joe.andhuber.login.model;

import joe.githubapi.model.ErrorInfo;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public interface IUser {
    void login(User user, String code, LoginCallBack callBack);

    interface LoginCallBack {
        void loginSuccess();

        void loginFailed(ErrorInfo errorInfo);
    }

    void getUserInfo(String token, GetUserInfoCallBack callBack);

    interface GetUserInfoCallBack {
        void getSuccessfully();

        void getFailed(ErrorInfo info);
    }
}
