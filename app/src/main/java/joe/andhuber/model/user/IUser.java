package joe.andhuber.model.user;

import joe.andhuber.model.activity.IUserCallBack;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public interface IUser {
    void login(User user, String code, IUserCallBack<Void> callBack);

    void logout();

    void getUserInfo(String username, String token, IUserCallBack<UserInfo> callBack);

    void checkAuthorization(String token, IUserCallBack<Void> callBack);
}
