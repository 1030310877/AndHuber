package joe.andhuber.user.presenter;

import android.content.Intent;

import joe.andhuber.user.view.UserDetailView;
import joe.githubapi.model.user.UserInfo;
import joe.githubapi.model.user.UserParam;

/**
 * Description
 * Created by chenqiao on 2016/8/1.
 */
public class UserDetailPresenterImpl implements UserDetailPresenter {

    private UserDetailView view;
    private UserInfo user;

    public UserDetailPresenterImpl(UserDetailView view) {
        this.view = view;
    }

    @Override
    public void getUserInfo(Intent intent) {
        user = (UserInfo) intent.getSerializableExtra("user");
        showInfo(user);
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
    }

    @Override
    public void saveUser(UserParam param) {

    }
}