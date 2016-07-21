package joe.andhuber.function.event.presenter;

import java.util.List;

import joe.andhuber.config.UserConfig;
import joe.andhuber.function.event.view.EventView;
import joe.andhuber.model.activity.ActivityModel;
import joe.andhuber.model.activity.EventParams;
import joe.andhuber.model.activity.IActivityCallBack;
import joe.githubapi.model.event.EventInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/13.
 */
public class EventPresenterImpl implements EventPresenter {

    private EventView eventView;
    private ActivityModel activityModel;

    public EventPresenterImpl(EventView view) {
        this.eventView = view;
        activityModel = new ActivityModel();
    }

    @Override
    public void getUserEvents(String username) {
        EventParams params = new EventParams();
        params.setAccess_token(UserConfig.getInstance().getToken());
        activityModel.getUsersEvents(username, params, new IActivityCallBack<List<EventInfo>>() {
            @Override
            public void onSuccessfully(List<EventInfo> result) {
                eventView.showEvents(result);
            }

            @Override
            public void onFailed(String message) {

            }
        });
    }
}
