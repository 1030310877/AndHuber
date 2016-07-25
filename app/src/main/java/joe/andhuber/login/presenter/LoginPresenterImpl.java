package joe.andhuber.login.presenter;

import joe.andhuber.config.UserConfig;
import joe.andhuber.login.view.LoginView;
import joe.andhuber.model.user.IUser;
import joe.andhuber.model.user.User;
import joe.andhuber.model.user.UserModel;
import joe.andhuber.utils.rx.RxView;
import joe.githubapi.model.ErrorInfo;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    private UserModel userModel;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        userModel = new UserModel();
    }

    @Override
    public void init() {
        if (UserConfig.getInstance().isRememberUser()) {
            view.setUserName(UserConfig.getInstance().getDefaultUser());
            view.setPassword(UserConfig.getInstance().getDefaultUserPassword());
            view.setRememberChecked(true);
        }
    }

    @Override
    public void login(User user, String code) {
        view.showWaitDialog();
        userModel.login(user, code, new IUser.LoginCallBack() {
            @Override
            public void loginSuccess() {
                if (UserConfig.getInstance().isRememberUser()) {
                    UserConfig.getInstance().setDefaultUser(user.getUserName(), user.getPassWord());
                } else {
                    UserConfig.getInstance().clearDefaultUser();
                }
                view.dismissWaitDialog();
                view.startToMain(user.getUserName());
            }

            @Override
            public void loginFailed(ErrorInfo errorInfo) {
                if (errorInfo.getMessage().equals("required")) {
                    view.dismissWaitDialog();
                    view.showTwoFactorDialog();
                } else {
                    view.dismissWaitDialog();
                    view.showError(errorInfo.getMessage());
                }
            }
        });
    }

    @Override
    public void rememberUser(boolean tf) {
        UserConfig.getInstance().setRememberUser(tf);
    }

    @Override
    public void setLoginAuto(boolean tf) {
        UserConfig.getInstance().setLoginAuto(tf);
    }

    @Override
    public void destroy() {
        RxView.release("Login");
    }
}