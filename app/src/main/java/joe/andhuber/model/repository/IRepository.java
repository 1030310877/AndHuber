package joe.andhuber.model.repository;

import rx.Subscription;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface IRepository {
    Subscription getUserRepositories(String username, RepositoryParams param, IRepositoryCallBack callBack);

    interface IRepositoryCallBack {
        void onSuccessfully(Object result);

        void onFailed(String message);
    }
}