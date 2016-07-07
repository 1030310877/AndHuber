package joe.andhuber.main.model;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface IRepository {
    void getUsersRepositories(RepositoryParams param, IRepositoryCallBack callBack);

    interface IRepositoryCallBack {
        void getSuccessfully(Object result);

        void getFailed(String message);
    }
}