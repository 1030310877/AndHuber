package joe.andhuber.model.activity;

import java.util.HashMap;
import java.util.List;

import joe.andhuber.utils.MapUtil;
import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.ErrorInfo;
import joe.githubapi.model.event.EventInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.rx.HttpSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public class ActivityModel {
    public Subscription getUsersStars(String username, StarParams params, IActivityCallBack<List<RepositoryInfo>> callBack) {
        HashMap<String, String> param = MapUtil.toMap(params);
        return GitHubApi.getActivityApi().getUserStars(username, param)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<RepositoryInfo>>() {
                    @Override
                    public void onHttpError(ErrorInfo info) {
                        if (callBack != null) {
                            callBack.onFailed(info.getMessage());
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(List<RepositoryInfo> repositoryInfo) {
                        if (callBack != null) {
                            callBack.onSuccessfully(repositoryInfo);
                        }
                    }
                });
    }

    public Subscription getUsersEvents(String username, EventParams params, IActivityCallBack<List<EventInfo>> callBack) {
        HashMap<String, String> param = MapUtil.toMap(params);
        return GitHubApi.getActivityApi().getUsersEvents(username, param)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<EventInfo>>() {
                    @Override
                    public void onHttpError(ErrorInfo info) {
                        if (callBack != null) {
                            callBack.onFailed(info.getMessage());
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(List<EventInfo> eventInfo) {
                        if (callBack != null) {
                            callBack.onSuccessfully(eventInfo);
                        }
                    }
                });
    }
}