package joe.andhuber.model.repository;

import java.util.HashMap;
import java.util.List;

import joe.andhuber.utils.MapUtil;
import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.ErrorInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.rx.HttpSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class RepositoryModel implements IRepository {
    @Override
    public Subscription getUserRepositories(String username, RepositoryParams param, IRepositoryCallBack callBack) {
        HashMap<String, String> params = MapUtil.toMap(param);
        return GitHubApi.getRepositoriesApi()
                .getUserRepositories(username, params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<RepositoryInfo>>() {
                    @Override
                    public void onHttpError(ErrorInfo info) {
                        callBack.onFailed(info.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(List<RepositoryInfo> repositoryInfo) {
                        callBack.onSuccessfully(repositoryInfo);
                    }
                });
    }
}
