package joe.andhuber.repository.presenter;

import joe.andhuber.HuberApplication;
import joe.andhuber.model.repository.IRepositoryCallBack;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.repository.view.ReadMeView;
import joe.andhuber.utils.ToastUtil;

/**
 * Description
 * Created by chenqiao on 2016/7/28.
 */
public class ReadMePresenterImpl implements ReadMePresenter {

    private ReadMeView view;
    private RepositoryModel model;

    public ReadMePresenterImpl(ReadMeView view) {
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
                view.showReadMe(result);
            }

            @Override
            public void onFailed(String message) {
                view.dismissWaitDialog();
                ToastUtil.show(HuberApplication.getInstance(), message);
                view.showReadMe("There is no ReadME.md found");
            }
        });
    }
}
