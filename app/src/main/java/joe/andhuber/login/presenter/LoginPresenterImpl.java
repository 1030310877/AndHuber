package joe.andhuber.login.presenter;

import joe.andhuber.config.UserConfig;
import joe.andhuber.login.model.IUser;
import joe.andhuber.login.model.User;
import joe.andhuber.login.model.UserModel;
import joe.andhuber.login.view.LoginView;
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
        view.showWait();
        userModel.login(user, code, new IUser.LoginCallBack() {
            @Override
            public void loginSuccess() {
                if (UserConfig.getInstance().isRememberUser()) {
                    UserConfig.getInstance().setDefaultUser(user.getUserName(), user.getPassWord());
                } else {
                    UserConfig.getInstance().clearDefaultUser();
                }
                getUserInfo(UserConfig.getInstance().getToken());
            }

            @Override
            public void loginFailed(ErrorInfo errorInfo) {
                if (errorInfo.getMessage().equals("required")) {
                    view.dismissWait();
                    view.showTwoFactorDialog();
                } else {
                    view.dismissWait();
                    view.showError(errorInfo.getMessage());
                }
            }
        });
    }

    @Override
    public void getUserInfo(String token) {
        userModel.getUserInfo(token, new IUser.GetUserInfoCallBack() {
            @Override
            public void getSuccessfully() {
                view.dismissWait();
                view.startToMain();
            }

            @Override
            public void getFailed(ErrorInfo info) {
                view.dismissWait();
                view.showError(info.getMessage());
            }
        });
    }

    @Override
    public void rememberUser(boolean tf) {
        UserConfig.getInstance().setRememberUser(tf);
    }

    @Override
    public void destroy() {
        RxView.release("Login");
    }
}