package joe.andhuber.main.presenter;

import java.util.List;

import joe.andhuber.config.UserConfig;
import joe.andhuber.main.model.IRepository;
import joe.andhuber.main.model.RepositoryModel;
import joe.andhuber.main.model.RepositoryParams;
import joe.andhuber.main.view.MainView;
import joe.andhuber.main.view.RepositoryView;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private RepositoryModel repositoryModel;
    private RepositoryView repositoryView;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        repositoryModel = new RepositoryModel();
    }

    @Override
    public void setRepositoryView(RepositoryView repositoryView) {
        this.repositoryView = repositoryView;
    }

    @Override
    public void initInformation() {
        view.showWaitDialog();
        initUserViews(UserConfig.nowUser);
        getUsersRepositories();
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
            public void getSuccessfully(Object result) {
                List<RepositoryInfo> repositories = (List<RepositoryInfo>) result;
                repositoryView.showRepositories(repositories);
                view.dismissWaitDialog();
            }

            @Override
            public void getFailed(String message) {
                view.dismissWaitDialog();
            }
        });
    }
}