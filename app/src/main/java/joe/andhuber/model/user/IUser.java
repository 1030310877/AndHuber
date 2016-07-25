package joe.andhuber.model.user;

import joe.githubapi.model.ErrorInfo;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public interface IUser {
    void login(User user, String code, LoginCallBack callBack);

    void logout();

    interface LoginCallBack {
        void loginSuccess();

        void loginFailed(ErrorInfo errorInfo);
    }

    void getUserInfo(String username, String token, GetUserInfoCallBack callBack);

    interface GetUserInfoCallBack {
        void getSuccessfully(UserInfo userInfo);

        void getFailed(ErrorInfo info);
    }

    void checkAuthorization(String token, CheckCallBack callBack);

    interface CheckCallBack {
        void checkSuccess();

        void checkFailed();
    }
}
