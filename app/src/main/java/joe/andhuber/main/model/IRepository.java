package joe.andhuber.main.model;

import rx.Subscription;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface IRepository {
    Subscription getUsersRepositories(RepositoryParams param, IRepositoryCallBack callBack);

    interface IRepositoryCallBack {
        void onSuccessfully(Object result);

        void onFailed(String message);
    }
}