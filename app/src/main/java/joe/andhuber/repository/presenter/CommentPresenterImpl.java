package joe.andhuber.repository.presenter;

import com.joe.rxbus.RxBus;

import java.util.Arrays;
import java.util.List;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.base.BaseParam;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.repository.IRepositoryCallBack;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.repository.view.CommentView;
import joe.andhuber.utils.MapUtil;
import joe.githubapi.model.repositories.CommentInfo;
import joe.githubapi.model.repositories.CommentParam;

/**
 * Description
 * Created by chenqiao on 2016/9/2.
 */
public class CommentPresenterImpl implements CommentPresenter {

    private CommentView view;
    private RepositoryModel model;

    public CommentPresenterImpl(CommentView view) {
        this.view = view;
        model = new RepositoryModel();
    }

    @Override
    public void getCommentsForACommit(String owner, String repo, String ref, BaseParam param) {
        param.setAccess_token(UserConfig.getInstance().getToken());
        model.getCommentsForACommit(owner, repo, ref, MapUtil.toMap(param), new IRepositoryCallBack<List<CommentInfo>>() {
            @Override
            public void onSuccessfully(List<CommentInfo> result) {
                view.addComments(result);
                view.refreshFinish();
                if (result != null && result.size() > 0) {
                    view.setPage(param.getPage());
                    view.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_success));
                } else {
                    view.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_to_bottom));
                }
            }

            @Override
            public void onFailed(String message) {
                view.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_failed));
            }
        });
    }

    @Override
    public void createACommentForACommit(String owner, String repo, String sha, String message) {
        CommentParam param = new CommentParam();
        param.setBody(message);
        model.createACommentForCommit(owner, repo, sha, param, UserConfig.getInstance().getToken(), new IRepositoryCallBack<CommentInfo>() {
            @Override
            public void onSuccessfully(CommentInfo result) {
                view.dismissWaitDialog();
                view.addComments(Arrays.asList(result));
                RxBus.getInstance().post("", "updateCommitInfo");
            }

            @Override
            public void onFailed(String message) {
                view.dismissWaitDialog();
            }
        });
    }
}
