package joe.andhuber.user.presenter;

import com.joe.rxbus.RxBus;

import joe.andhuber.config.UserConfig;
import joe.andhuber.model.user.IUser;
import joe.andhuber.model.user.UserModel;
import joe.andhuber.user.view.UserMainView;
import joe.githubapi.model.ErrorInfo;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class UserMainPresenterImpl implements UserMainPresenter {

    private UserMainView view;
    private UserModel model;

    public UserMainPresenterImpl(UserMainView view) {
        this.view = view;
        model = new UserModel();
    }

    public void initUserViews(UserInfo userInfo) {
        view.setTitle(userInfo.getName());
        view.setHeadImg(userInfo.getAvatar_url());
        view.setBlog(userInfo.getBlog());
        view.setCompany(userInfo.getCompany());
        view.setEmail(userInfo.getEmail());
        view.setFollower(userInfo.getFollowers());
        view.setFollowing(userInfo.getFollowing());
    }

    @Override
    public void getUserInfo(String loginName) {
        view.showWaitDialog();
        model.getUserInfo(loginName, UserConfig.getInstance().getToken(), new IUser.GetUserInfoCallBack() {

            @Override
            public void getSuccessfully(UserInfo userInfo) {
                if (view.isHome()) {
                    UserConfig.nowUser = userInfo;
                }
                view.dismissWaitDialog();
                initUserViews(userInfo);
                view.initFragments(userInfo);
            }

            @Override
            public void getFailed(ErrorInfo info) {
                view.dismissWaitDialog();
            }
        });
    }

    @Override
    public void refreshData(int nowIndex) {
        switch (nowIndex) {
            case 0:
                RxBus.getInstance().post("refreshRepository", "refreshRepository");
                break;
            case 1:
                RxBus.getInstance().post("refreshStar", "refreshStars");
                break;
        }
    }
}