package joe.andhuber.user.presenter;

import com.joe.rxbus.RxBus;

import joe.andhuber.user.view.UserMainView;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class UserMainPresenterImpl implements UserMainPresenter {

    private UserMainView view;
    private UserInfo user;

    public UserMainPresenterImpl(UserMainView view, UserInfo user) {
        this.view = view;
        this.user = user;
    }

    @Override
    public void initInformation() {
        initUserViews(user);
    }

    @Override
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