package joe.andhuber.wel;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.activity.IUserCallBack;
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
        userModel.checkAuthorization(UserConfig.getInstance().getToken(), new IUserCallBack<Void>() {
            @Override
            public void onSuccess(Void result) {
                view.startToMain(UserConfig.getInstance().getDefaultUser());
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), R.string.authorization_failed);
                view.startToLogin();
            }
        });
    }
}
