package joe.andhuber.login.presenter;

import joe.andhuber.model.user.User;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public interface LoginPresenter {
    void init();

    void login(User user, String code);

    void rememberUser(boolean tf);

    void setLoginAuto(boolean tf);

    void destroy();
}
