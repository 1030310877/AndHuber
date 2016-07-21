package joe.andhuber.model.search;

/**
 * Description
 * Created by chenqiao on 2016/7/21.
 */
public interface ISearchCallBack<T> {
    void onSuccessfully(T result);

    void onFailed(String errorInfo);
}
