package joe.andhuber.wel;

import joe.andhuber.HuberApplication;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.user.IUser;
import joe.andhuber.model.user.UserModel;
import joe.andhuber.utils.ToastUtil;
import joe.githubapi.model.ErrorInfo;

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
    public void getUserInfo(String username, String token) {
        userModel.getUserInfo(username, token, new IUser.GetUserInfoCallBack() {
            @Override
            public void getSuccessfully() {
                view.startToMain(UserConfig.nowUser);
            }

            @Override
            public void getFailed(ErrorInfo info) {
                view.startToLogin();
                ToastUtil.show(HuberApplication.getInstance(), info.getMessage());
            }
        });
    }
}
