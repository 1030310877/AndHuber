package joe.andhuber.user.presenter;

import java.util.List;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.activity.ActivityModel;
import joe.andhuber.model.activity.IActivityCallBack;
import joe.andhuber.model.activity.StarParams;
import joe.andhuber.user.view.StarView;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class StarPresenterImpl implements StarPresenter {
    private ActivityModel activityModel;
    private StarView starView;

    public StarPresenterImpl(StarView starView) {
        this.starView = starView;
        activityModel = new ActivityModel();
    }

    @Override
    public void getUserStars(String username, int page) {
        starView.showWaitDialog();
        StarParams params = new StarParams();
        params.setPage(page);
        params.setAccess_token(UserConfig.getInstance().getToken());
        activityModel.getUsersStars(username, params, new IActivityCallBack<List<RepositoryInfo>>() {
            @Override
            public void onSuccessfully(List<RepositoryInfo> result) {
                starView.dismissWaitDialog();
                starView.addStars(result);
                if (result != null && result.size() > 0) {
                    starView.setPage(page);
                    starView.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_success));
                } else {
                    starView.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_to_bottom));
                }
            }

            @Override
            public void onFailed(String message) {
                starView.dismissWaitDialog();
                starView.loadFinish(HuberApplication.getInstance().getResources().getString(R.string.load_failed));
            }
        });
    }
}
