package joe.andhuber.model.activity;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public interface IActivityCallBack {
    void onSuccessfully(Object result);

    void onFailed(String message);
}
