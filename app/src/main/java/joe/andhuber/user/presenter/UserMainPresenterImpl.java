package joe.andhuber.user.presenter;

import android.text.TextUtils;

import com.joe.rxbus.RxBus;

import joe.andhuber.config.UserConfig;
import joe.andhuber.model.activity.IUserCallBack;
import joe.andhuber.model.user.UserModel;
import joe.andhuber.user.view.UserMainView;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class UserMainPresenterImpl implements UserMainPresenter {

    private UserMainView view;
    private UserModel model;
    private UserInfo userInfo;

    public UserMainPresenterImpl(UserMainView view) {
        this.view = view;
        model = new UserModel();
    }

    @Override
    public void initUserViews(UserInfo userInfo) {
        if (TextUtils.isEmpty(userInfo.getName())) {
            view.setTitle(userInfo.getLogin());
        } else {
            view.setTitle(userInfo.getName());
        }
        view.setHeadImg(userInfo.getAvatar_url());
        view.setBlog(userInfo.getBlog());
        view.setCompany(userInfo.getCompany());
        view.setEmail(userInfo.getEmail());
        view.setFollower(userInfo.getFollowers());
        view.setFollowing(userInfo.getFollowing());
    }

    @Override
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void getUserInfoFromServer(String loginName) {
        view.showWaitDialog();
        model.getUserInfo(loginName, UserConfig.getInstance().getToken(), new IUserCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo result) {
                UserMainPresenterImpl.this.userInfo = result;
                if (view.isHome()) {
                    UserConfig.nowUser = userInfo;
                }
                view.dismissWaitDialog();
                initUserViews(userInfo);
                view.initFragments(userInfo);
            }

            @Override
            public void onFailed(String message) {
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