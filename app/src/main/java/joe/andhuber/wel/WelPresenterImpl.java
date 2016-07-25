package joe.andhuber.wel;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.user.IUser;
import joe.andhuber.model.user.UserModel;
import joe.andhuber.utils.ToastUtil;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public class WelPresenterImpl implements WelPresenter {
    private WelView view;
    private UserModel userModel;

    public WelPresenterImpl(WelView view) {
        this.view = view;
        userModel = new UserModel();
    }

    @Override
    public void checkAuthorization() {
        userModel.checkAuthorization(UserConfig.getInstance().getToken(), new IUser.CheckCallBack() {
            @Override
            public void checkSuccess() {
                view.startToMain(UserConfig.getInstance().getDefaultUser());
            }

            @Override
            public void checkFailed() {
                ToastUtil.show(HuberApplication.getInstance(), R.string.authorization_failed);
                view.startToLogin();
            }
        });
    }
}
