package joe.andhuber.repository.presenter;

import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.activity.ActivityModel;
import joe.andhuber.model.activity.IActivityCallBack;
import joe.andhuber.model.repository.IRepositoryCallBack;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.repository.view.RepoDetailView;
import joe.andhuber.utils.ToastUtil;
import joe.githubapi.model.repositories.ContentInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public class RepoDetailPresenterImpl implements RepoDetailPresenter {

    private RepoDetailView view;
    private RepositoryModel model;
    private ActivityModel activityModel;
    private boolean isLock = true;

    public RepoDetailPresenterImpl(RepoDetailView view) {
        this.view = view;
        model = new RepositoryModel();
        activityModel = new ActivityModel();
    }

    @Override
    public void getReadMe(String owner, String repo) {
        view.showWaitDialog();
        model.getReadMeOfRepo(owner, repo, new IRepositoryCallBack<String>() {
            @Override
            public void onSuccessfully(String result) {
                view.dismissWaitDialog();
                view.startToContentView("ReadMe.md", "", result);
            }

            @Override
            public void onFailed(String message) {
                view.dismissWaitDialog();
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void getFilesByPath(String owner, String repo, String path) {
        view.showWaitDialog();
        model.getRepoContent(owner, repo, path, UserConfig.getInstance().getToken(), new IRepositoryCallBack<List<ContentInfo>>() {
            @Override
            public void onSuccessfully(List<ContentInfo> result) {
                view.dismissWaitDialog();
                String[] temp = path.split(File.separator);
                if (result != null) {
                    view.showFiles(result);
                    List<String> temp2 = Arrays.asList(temp);
                    ArrayList<String> nowPath = new ArrayList<>(temp2);
                    if (nowPath.size() >= 1 && !TextUtils.isEmpty(nowPath.get(0))) {
                        nowPath.add(0, "");
                    }
                    view.showPath(nowPath);
                }
            }

            @Override
            public void onFailed(String message) {
                view.dismissWaitDialog();
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void checkIsStarred(String owner, String repo) {
        activityModel.isStarringRepo(owner, repo, UserConfig.getInstance().getToken(), new IActivityCallBack<Boolean>() {
            @Override
            public void onSuccessfully(Boolean result) {
                view.setStarred(result);
                isLock = false;
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
                isLock = true;
            }
        });
    }

    @Override
    public void starRepository(String owner, String repo) {
        if (isLock) {
            ToastUtil.show(HuberApplication.getInstance(), R.string.star_info_error);
            return;
        }
        ToastUtil.show(HuberApplication.getInstance(), R.string.doing_star);
        activityModel.starRepository(owner, repo, UserConfig.getInstance().getToken(), new IActivityCallBack<Boolean>() {
            @Override
            public void onSuccessfully(Boolean result) {
                if (result) {
                    view.setStarred(true);
                    ToastUtil.show(HuberApplication.getInstance(), R.string.star_success);
                } else {
                    ToastUtil.show(HuberApplication.getInstance(), R.string.star_failed);
                }
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void unStarRepository(String owner, String repo) {
        if (isLock) {
            ToastUtil.show(HuberApplication.getInstance(), R.string.star_info_error);
            return;
        }
        ToastUtil.show(HuberApplication.getInstance(), R.string.undo_star);
        activityModel.unStarRepository(owner, repo, UserConfig.getInstance().getToken(), new IActivityCallBack<Boolean>() {
            @Override
            public void onSuccessfully(Boolean result) {
                if (result) {
                    view.setStarred(false);
                } else {
                    ToastUtil.show(HuberApplication.getInstance(), R.string.undo_star_failed);
                }
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }
}