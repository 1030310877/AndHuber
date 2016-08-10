package joe.andhuber.repository.presenter;

import java.util.List;
import java.util.Map;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.repository.CommitParams;
import joe.andhuber.model.repository.IRepositoryCallBack;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.repository.view.CommitsView;
import joe.andhuber.utils.MapUtil;
import joe.githubapi.model.repositories.CommitInfo;
import rx.Subscription;

/**
 * Description
 * Created by chenqiao on 2016/8/9.
 */
public class CommitsPresenterImpl implements CommitsPresenter {

    private CommitsView view;
    private RepositoryModel model;
    private Subscription subscription;

    public CommitsPresenterImpl(CommitsView view) {
        this.view = view;
        model = new RepositoryModel();
    }

    @Override
    public void getCommitsOfRepo(String owner, String repo, CommitParams commitParams) {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
//        view.showWaitDialog();
        commitParams.setAccess_token(UserConfig.getInstance().getToken());
        Map<String, String> params = MapUtil.toMap(commitParams);
        subscription = model.getCommitsOfRepo(owner, repo, params, new IRepositoryCallBack<List<CommitInfo>>() {
            @Override
            public void onSuccessfully(List<CommitInfo> result) {
//                view.dismissWaitDialog();
                view.addCommits(result);
                view.refreshFinish();
                if (result != null && result.size() > 0) {
                    view.setPage(commitParams.getPage());
                    view.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_success));
                } else {
                    view.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_to_bottom));
                }
            }

            @Override
            public void onFailed(String message) {
//                view.dismissWaitDialog();
                view.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_failed));
            }
        });
    }
}
