package joe.andhuber.login.view;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public interface LoginView {

    void showWait();

    void dismissWait();

    void showError(String error);

    void showTwoFactorDialog();

    void startToMain();

    String getUserName();

    String getPassWord();

    void setUserName(String username);

    void setPassword(String password);

    void setRememberChecked(boolean isChecked);
}
