package joe.andhuber.user.presenter;

import java.util.List;

import joe.andhuber.config.UserConfig;
import joe.andhuber.model.repository.IRepositoryCallBack;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.model.repository.RepositoryParams;
import joe.andhuber.user.view.RepositoryView;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class RepositoryPresenterImpl implements RepositoryPresenter {
    private RepositoryModel repositoryModel;
    private RepositoryView repositoryView;

    public RepositoryPresenterImpl(RepositoryView repositoryView) {
        this.repositoryView = repositoryView;
        repositoryModel = new RepositoryModel();
    }

    @Override
    public void getUserRepositories(String username, int page) {
        repositoryView.showWaitDialog();
        RepositoryParams params = new RepositoryParams();
        params.setPage(page);
        params.setAccess_token(UserConfig.getInstance().getToken());
        repositoryModel.getUserRepositories(username, params, new IRepositoryCallBack() {
            @Override
            public void onSuccessfully(Object result) {
                repositoryView.dismissWaitDialog();
                List<RepositoryInfo> repositories = (List<RepositoryInfo>) result;
                repositoryView.addRepositories(repositories);
                if (repositories != null && repositories.size() > 0) {
                    repositoryView.setPage(page);
                    repositoryView.loadFinish("加载成功");
                } else {
                    repositoryView.loadFinish("已至最后一页");
                }
            }

            @Override
            public void onFailed(String message) {
                repositoryView.dismissWaitDialog();
                repositoryView.loadFinish("加载失败");
            }
        });
    }
}
