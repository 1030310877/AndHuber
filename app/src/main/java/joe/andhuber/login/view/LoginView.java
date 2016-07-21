package joe.andhuber.login.view;

import joe.andhuber.base.BaseView;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public interface LoginView extends BaseView {

    void showError(String error);

    void showTwoFactorDialog();

    void startToMain(UserInfo user);

    String getUserName();

    String getPassWord();

    void setUserName(String username);

    void setPassword(String password);

    void setRememberChecked(boolean isChecked);
}
