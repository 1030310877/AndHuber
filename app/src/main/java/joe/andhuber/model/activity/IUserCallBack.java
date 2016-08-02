package joe.andhuber.model.activity;

/**
 * Description
 * Created by chenqiao on 2016/8/2.
 */
public interface IUserCallBack<T> {
    void onSuccess(T result);

    void onFailed(String message);
}