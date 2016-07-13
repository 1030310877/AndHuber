package joe.andhuber.login.view;

import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public interface LoginView {

    void showWait();

    void dismissWait();

    void showError(String error);

    void showTwoFactorDialog();

    void startToMain(UserInfo user);

    String getUserName();

    String getPassWord();

    void setUserName(String username);

    void setPassword(String password);

    void setRememberChecked(boolean isChecked);
}
