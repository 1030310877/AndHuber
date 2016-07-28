package joe.andhuber.repository.presenter;

import java.io.IOException;

import joe.andhuber.HuberApplication;
import joe.andhuber.model.repository.IRepositoryCallBack;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.repository.activity.FileContentActivity;
import joe.andhuber.repository.view.FileContentView;
import joe.andhuber.utils.ToastUtil;
import okhttp3.ResponseBody;

/**
 * Description
 * Created by chenqiao on 2016/7/27.
 */
public class FileContentPresenterImpl implements FileContentPresenter {
    private FileContentView view;
    private RepositoryModel model;

    public FileContentPresenterImpl(FileContentView view) {
        this.view = view;
        model = new RepositoryModel();
    }

    @Override
    public void getFileRaw(String url, int type) {
        view.showWaitDialog();
        model.getFileContent(url, new IRepositoryCallBack<ResponseBody>() {
            @Override
            public void onSuccessfully(ResponseBody result) {
                view.dismissWaitDialog();
                try {
                    String response = result.string();
                    switch (type) {
                        case FileContentActivity.CODE:
                            view.showCode(response);
                            break;
                        case FileContentActivity.TEXT:
                            view.showText(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(String message) {
                view.dismissWaitDialog();
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }
}
