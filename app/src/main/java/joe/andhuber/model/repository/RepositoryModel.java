package joe.andhuber.model.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import joe.andhuber.config.UserConfig;
import joe.andhuber.utils.MapUtil;
import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.ErrorInfo;
import joe.githubapi.model.repositories.ContentInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.rx.HttpSubscriber;
import okhttp3.ResponseBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class RepositoryModel {
    public Subscription getUserRepositories(String username, RepositoryParams param, IRepositoryCallBack callBack) {
        HashMap<String, String> params = MapUtil.toMap(param);
        return GitHubApi.getRepositoriesApi()
                .getUserRepositories(username, params)
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

    public Subscription getReadMeOfRepo(String owner, String repo, IRepositoryCallBack callBack) {
        return GitHubApi.getRepositoriesApi().getReadMeForHtml(owner, repo, UserConfig.getInstance().getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseBody body) {
                        if (callBack != null) {
                            try {
                                callBack.onSuccessfully(body.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onHttpError(ErrorInfo info) {
                        if (callBack != null) {
                            callBack.onFailed(info.getMessage());
                        }
                    }
                });
    }

    public Subscription getRepoContent(String owner, String repo, String path, IRepositoryCallBack callBack) {
        return GitHubApi.getRepositoriesApi().getRepoContent(owner, repo, path, UserConfig.getInstance().getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<ContentInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(List<ContentInfo> contentInfo) {
                        if (callBack != null) {
                            callBack.onSuccessfully(contentInfo);
                        }
                    }

                    @Override
                    public void onHttpError(ErrorInfo info) {
                        if (callBack != null) {
                            callBack.onFailed(info.getMessage());
                        }
                    }
                });
    }

    public Subscription getFileContentForRaw(String owner, String repo, String path, IRepositoryCallBack callBack) {
        return GitHubApi.getRepositoriesApi().getRepoFileContentForHtml(owner, repo, path, UserConfig.getInstance().getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (callBack != null) {
                            try {
                                callBack.onSuccessfully(responseBody.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onHttpError(ErrorInfo info) {
                        if (callBack != null) {
                            callBack.onFailed(info.getMessage());
                        }
                    }
                });
    }
}
