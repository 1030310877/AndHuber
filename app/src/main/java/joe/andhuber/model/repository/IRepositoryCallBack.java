package joe.andhuber.model.repository;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface IRepositoryCallBack {
    void onSuccessfully(Object result);

    void onFailed(String message);
}