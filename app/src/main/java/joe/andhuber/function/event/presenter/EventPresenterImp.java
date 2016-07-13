package joe.andhuber.function.event.presenter;

import java.util.List;

import joe.andhuber.config.UserConfig;
import joe.andhuber.model.activity.ActivityModel;
import joe.andhuber.model.activity.EventParams;
import joe.andhuber.model.activity.IActivity;
import joe.andhuber.user.view.EventView;
import joe.githubapi.model.event.EventInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/13.
 */
public class EventPresenterImp implements EventPresenter {

    private EventView eventView;
    private ActivityModel activityModel;

    public EventPresenterImp(EventView view) {
        this.eventView = view;
        activityModel = new ActivityModel();
    }

    @Override
    public void getUserEvents(String username) {
        EventParams params = new EventParams();
        params.setAccess_token(UserConfig.getInstance().getToken());
        activityModel.getUsersEvents(username, params, new IActivity.IActivityCallBack() {
            @Override
            public void onSuccessfully(Object result) {
                List<EventInfo> events = (List<EventInfo>) result;
                eventView.showEvents(events);
            }

            @Override
            public void onFailed(String message) {

            }
        });
    }
}
