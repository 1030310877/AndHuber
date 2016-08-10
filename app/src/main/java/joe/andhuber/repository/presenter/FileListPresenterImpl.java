package joe.andhuber.repository.presenter;

import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import joe.andhuber.HuberApplication;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.repository.IRepositoryCallBack;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.repository.view.FileListView;
import joe.andhuber.utils.ToastUtil;
import joe.githubapi.model.repositories.ContentInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/28.
 */
public class FileListPresenterImpl implements FileListPresenter {

    private FileListView view;
    private RepositoryModel model;

    public FileListPresenterImpl(FileListView view) {
        this.view = view;
        model = new RepositoryModel();
    }

    @Override
    public void getFilesByPath(String owner, String repo, String path) {
//        view.showWaitDialog();
        model.getContentOfRepo(owner, repo, path, UserConfig.getInstance().getToken(), new IRepositoryCallBack<List<ContentInfo>>() {
            @Override
            public void onSuccessfully(List<ContentInfo> result) {
//                view.dismissWaitDialog();
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
//                view.dismissWaitDialog();
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }
}
