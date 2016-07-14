package joe.andhuber.user.presenter;

import java.util.List;

import joe.andhuber.config.UserConfig;
import joe.andhuber.model.activity.ActivityModel;
import joe.andhuber.model.activity.IActivity;
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
        activityModel.getUsersStars(username, params, new IActivity.IActivityCallBack() {
            @Override
            public void onSuccessfully(Object result) {
                starView.dismissWaitDialog();
                List<RepositoryInfo> repositories = (List<RepositoryInfo>) result;
                starView.addStars(repositories);
                if (repositories != null && repositories.size() > 0) {
                    starView.setPage(page);
                    starView.loadFinish("加载成功");
                } else {
                    starView.loadFinish("已至最后一页");
                }
            }

            @Override
            public void onFailed(String message) {
                starView.dismissWaitDialog();
                starView.loadFinish("加载失败");
            }
        });
    }
}
