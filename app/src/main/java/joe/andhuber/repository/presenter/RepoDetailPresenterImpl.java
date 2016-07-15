package joe.andhuber.repository.presenter;

import joe.andhuber.model.repository.IRepositoryCallBack;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.repository.view.RepoDetailView;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public class RepoDetailPresenterImpl implements RepoDetailPresenter {

    private RepoDetailView view;
    private RepositoryModel model;

    public RepoDetailPresenterImpl(RepoDetailView view) {
        this.view = view;
        model = new RepositoryModel();
    }

    @Override
    public void getReadMe(String owner, String repo) {
        view.showWaitDialog();
        model.getReadMeOfRepo(owner, repo, new IRepositoryCallBack() {
            @Override
            public void onSuccessfully(Object result) {
                view.dismissWaitDialog();
                if (result instanceof String) {
                    view.loadReadMe((String) result);
                }
            }

            @Override
            public void onFailed(String message) {
                view.dismissWaitDialog();
            }
        });
    }
}
