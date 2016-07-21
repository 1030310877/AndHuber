package joe.andhuber.repository.presenter;

import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import joe.andhuber.HuberApplication;
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

    public RepoDetailPresenterImpl(RepoDetailView view) {
        this.view = view;
        model = new RepositoryModel();
    }

    @Override
    public void getReadMe(String owner, String repo) {
        view.showWaitDialog();
        model.getReadMeOfRepo(owner, repo, new IRepositoryCallBack<String>() {
            @Override
            public void onSuccessfully(String result) {
                view.dismissWaitDialog();
                view.startToContentView("ReadMe", result, 0);
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
        model.getRepoContent(owner, repo, path, new IRepositoryCallBack<List<ContentInfo>>() {
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
    public void getFileContentByPathForRaw(String owner, String repo, String path) {
        view.showWaitDialog();
        model.getFileContentForRaw(owner, repo, path, new IRepositoryCallBack<String>() {
            @Override
            public void onSuccessfully(String result) {
                view.dismissWaitDialog();
                String[] temp = path.split(File.separator);
                view.startToContentView(temp[temp.length - 1], result, 1);
            }

            @Override
            public void onFailed(String message) {
                view.dismissWaitDialog();
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }
}