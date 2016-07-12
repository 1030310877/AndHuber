package joe.andhuber.main.model;

import rx.Subscription;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public interface IActivity {

    Subscription getNowUsersStars(StarParams params, IActivityCallBack callBack);

    Subscription getUsersEvents(String username, EventParams params, IActivityCallBack callBack);

    interface IActivityCallBack {
        void onSuccessfully(Object result);

        void onFailed(String message);
    }
}
