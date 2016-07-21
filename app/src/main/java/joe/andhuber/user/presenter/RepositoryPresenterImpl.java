package joe.andhuber.user.presenter;

import java.util.List;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
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
        repositoryModel.getUserRepositories(username, params, new IRepositoryCallBack<List<RepositoryInfo>>() {
            @Override
            public void onSuccessfully(List<RepositoryInfo> result) {
                repositoryView.dismissWaitDialog();
                repositoryView.addRepositories(result);
                if (result != null && result.size() > 0) {
                    repositoryView.setPage(page);
                    repositoryView.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_success));
                } else {
                    repositoryView.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_to_bottom));
                }
            }

            @Override
            public void onFailed(String message) {
                repositoryView.dismissWaitDialog();
                repositoryView.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_failed));
            }
        });
    }
}
