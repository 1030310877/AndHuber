package joe.andhuber.login.presenter;

import joe.andhuber.login.model.User;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public interface LoginPresenter {
    void init();

    void login(User user, String code);

    void getUserInfo(String token);

    void rememberUser(boolean tf);

    void destroy();
}
