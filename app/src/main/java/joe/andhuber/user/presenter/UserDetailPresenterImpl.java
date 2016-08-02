package joe.andhuber.user.presenter;

import android.content.Intent;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.activity.IUserCallBack;
import joe.andhuber.model.user.UserModel;
import joe.andhuber.user.view.UserDetailView;
import joe.andhuber.utils.ToastUtil;
import joe.githubapi.model.user.UserInfo;
import joe.githubapi.model.user.UserParam;

/**
 * Description
 * Created by chenqiao on 2016/8/1.
 */
public class UserDetailPresenterImpl implements UserDetailPresenter {

    private UserDetailView view;
    private UserInfo user;
    private UserModel model;

    public UserDetailPresenterImpl(UserDetailView view) {
        this.view = view;
        model = new UserModel();
    }

    @Override
    public UserInfo getUserInfo() {
        return user;
    }

    @Override
    public void getUserInfo(Intent intent) {
        user = (UserInfo) intent.getSerializableExtra("user");
        showInfo(user);
        if (user.getLogin().equals(UserConfig.nowUser.getLogin())) {
            view.showEditImg(true);
        } else {
            view.showEditImg(false);
        }
    }

    @Override
    public void showInfo(UserInfo info) {
        view.showHeadImg(user.getAvatar_url());
        view.showLoginName(user.getLogin());
        view.showFollowers(user.getFollowers());
        view.showFollowing(user.getFollowing());
        view.showName(user.getName());
        view.showEmail(user.getEmail());
        view.showBlog(user.getBlog());
        view.showCompany(user.getCompany());
        view.showLocation(user.getLocation());
        view.showBio(user.getBio());
        view.showHireable(user.isHireable());
    }

    @Override
    public void saveUser(UserParam param) {
        view.showWaitDialog();
        if (!user.getLogin().equals(UserConfig.nowUser.getLogin())) {
            ToastUtil.show(HuberApplication.getInstance(), R.string.no_access_to_edit_other_profile);
            return;
        }
        model.updateUserInfo(param, UserConfig.getInstance().getToken(), new IUserCallBack<UserInfo>() {
            @Override
            public void onSuccess(UserInfo result) {
                view.dismissWaitDialog();
                user = result;
                showInfo(user);
                view.setEditable(false);
                view.setChanged(true);
            }

            @Override
            public void onFailed(String message) {
                view.dismissWaitDialog();
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void initInfo() {
        if (user != null) {
            showInfo(user);
        }
    }
}