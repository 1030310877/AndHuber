package joe.andhuber.model.search;

import java.util.Map;

import joe.andhuber.utils.MapUtil;
import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.search.SearchRepoResult;
import joe.githubapi.model.search.SearchUserResult;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description
 * Created by chenqiao on 2016/7/21.
 */
public class SearchModel {

    public Subscription searchUsers(SearchParam param, ISearchCallBack<SearchUserResult> callBack) {
        Map<String, String> p = MapUtil.toMap(param);
        return GitHubApi.getSearchApi().searchUser(p)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchUserResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed(e.toString());
                    }

                    @Override
                    public void onNext(SearchUserResult searchUserResult) {
                        callBack.onSuccessfully(searchUserResult);
                    }
                });
    }

    public Subscription searchRepositories(SearchParam param, ISearchCallBack<SearchRepoResult> callBack) {
        Map<String, String> p = MapUtil.toMap(param);
        return GitHubApi.getSearchApi().searchRepository(p)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchRepoResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed(e.toString());
                    }

                    @Override
                    public void onNext(SearchRepoResult result) {
                        callBack.onSuccessfully(result);
                    }
                });
    }
}
