package joe.andhuber.main.presenter;

import java.util.List;

import joe.andhuber.config.UserConfig;
import joe.andhuber.main.model.ActivityModel;
import joe.andhuber.main.model.IActivity;
import joe.andhuber.main.model.IRepository;
import joe.andhuber.main.model.RepositoryModel;
import joe.andhuber.main.model.RepositoryParams;
import joe.andhuber.main.model.StarParams;
import joe.andhuber.main.view.EventView;
import joe.andhuber.main.view.MainView;
import joe.andhuber.main.view.RepositoryView;
import joe.andhuber.main.view.StarView;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private RepositoryModel repositoryModel;
    private ActivityModel activityModel;
    private RepositoryView repositoryView;
    private StarView starView;
    private EventView eventView;

    public MainPresenterImpl(MainView view) {
        this.view = view;
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
    public void setEventView(EventView view) {
        this.eventView = view;
    }

    @Override
    public void initInformation() {
        view.showWaitDialog();
        initUserViews(UserConfig.nowUser);
        getUsersRepositories();
        getUsersStars();
    }

    @Override
    public void initUserViews(UserInfo userInfo) {
        view.setTitle(userInfo.getName());
        view.setHeadImg(userInfo.getAvatar_url());
    }

    @Override
    public void getUsersRepositories() {
        RepositoryParams params = new RepositoryParams();
        params.setAccess_token(UserConfig.getInstance().getToken());
        repositoryModel.getUsersRepositories(params, new IRepository.IRepositoryCallBack() {
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
    public void getUsersStars() {
        StarParams params = new StarParams();
        params.setAccess_token(UserConfig.getInstance().getToken());
        activityModel.getNowUsersStars(params, new IActivity.IActivityCallBack() {
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
    public void getUsersEvents() {

    }

    @Override
    public void refreshData(int nowIndex) {
        view.showWaitDialog();
        switch (nowIndex) {
            case 0:
                getUsersRepositories();
                break;
            case 1:
                getUsersStars();
                break;
            case 2:
                getUsersEvents();
                break;
        }
    }
}