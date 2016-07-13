package joe.andhuber.user.presenter;

import java.util.List;

import joe.andhuber.config.UserConfig;
import joe.andhuber.model.activity.ActivityModel;
import joe.andhuber.model.activity.IActivity;
import joe.andhuber.model.repository.IRepository;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.model.repository.RepositoryParams;
import joe.andhuber.model.activity.StarParams;
import joe.andhuber.user.view.RepositoryView;
import joe.andhuber.user.view.StarView;
import joe.andhuber.user.view.UserMainView;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class UserMainPresenterImpl implements UserMainPresenter {

    private UserMainView view;
    private RepositoryModel repositoryModel;
    private ActivityModel activityModel;
    private RepositoryView repositoryView;
    private StarView starView;
    private UserInfo user;

    public UserMainPresenterImpl(UserMainView view, UserInfo userInfo) {
        this.view = view;
        this.user = userInfo;
        repositoryModel = new RepositoryModel();
        activityModel = new ActivityModel();
    }

    @Override
    public void setStarView(StarView view) {
        starView = view;
    }

    @Override
    public void setRepositoryView(RepositoryView repositoryView) {
        this.repositoryView = repositoryView;
    }

    @Override
    public void initInformation() {
        view.showWaitDialog();
        initUserViews(user);
        getUserRepositories(user.getLogin());
        getUserStars(user.getLogin());
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
    public void getUserRepositories(String username) {
        RepositoryParams params = new RepositoryParams();
        params.setAccess_token(UserConfig.getInstance().getToken());
        repositoryModel.getUserRepositories(username, params, new IRepository.IRepositoryCallBack() {
            @Override
            public void onSuccessfully(Object result) {
                List<RepositoryInfo> repositories = (List<RepositoryInfo>) result;
                repositoryView.showRepositories(repositories);
                view.dismissWaitDialog();
            }

            @Override
            public void onFailed(String message) {
                view.dismissWaitDialog();
            }
        });
    }

    @Override
    public void getUserStars(String username) {
        StarParams params = new StarParams();
        params.setAccess_token(UserConfig.getInstance().getToken());
        activityModel.getUsersStars(username, params, new IActivity.IActivityCallBack() {
            @Override
            public void onSuccessfully(Object result) {
                List<RepositoryInfo> repositories = (List<RepositoryInfo>) result;
                starView.showStars(repositories);
                view.dismissWaitDialog();
            }

            @Override
            public void onFailed(String message) {

            }
        });
    }

    @Override
    public void refreshData(int nowIndex) {
        view.showWaitDialog();
        switch (nowIndex) {
            case 0:
                getUserRepositories(user.getLogin());
                break;
            case 1:
                getUserStars(user.getLogin());
                break;
        }
    }
}