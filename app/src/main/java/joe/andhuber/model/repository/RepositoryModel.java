package joe.andhuber.model.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import joe.andhuber.config.UserConfig;
import joe.andhuber.utils.MapUtil;
import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.ErrorInfo;
import joe.githubapi.model.repositories.CommentInfo;
import joe.githubapi.model.repositories.CommentParam;
import joe.githubapi.model.repositories.CommitInfo;
import joe.githubapi.model.repositories.ContentInfo;
import joe.githubapi.model.repositories.ForkParam;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.rx.HttpSubscriber;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class RepositoryModel {
    public Subscription getUserRepositories(String username, RepositoryParams param, IRepositoryCallBack<List<RepositoryInfo>> callBack) {
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

    public Subscription getReadMeOfRepo(String owner, String repo, IRepositoryCallBack<String> callBack) {
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

    public Subscription getContentOfRepo(String owner, String repo, String path, String token, IRepositoryCallBack<List<ContentInfo>> callBack) {
        return GitHubApi.getRepositoriesApi().getRepoContent(owner, repo, path, token)
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

    public Subscription getFileContentForRaw(String owner, String repo, String path, String token, IRepositoryCallBack<String> callBack) {
        return GitHubApi.getRepositoriesApi().getRepoFileContentForRaw(owner, repo, path, token)
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

    public Subscription getFileContentForHtml(String owner, String repo, String path, String token, IRepositoryCallBack<String> callBack) {
        return GitHubApi.getRepositoriesApi().getRepoFileContentForHtml(owner, repo, path, token)
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

    public Subscription getFileContent(String url, IRepositoryCallBack<ResponseBody> callBack) {
        return GitHubApi.getRepositoriesApi().getRepoFileContent(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseBody body) {
                        if (callBack != null) {
                            callBack.onSuccessfully(body);
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

    public Subscription forkRepository(String owner, String repo, String organization, String token, IRepositoryCallBack<RepositoryInfo> callBack) {
        ForkParam param = new ForkParam();
        param.setOrganization(organization);
        return GitHubApi.getRepositoriesApi().forkRepository(owner, repo, param, token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RepositoryInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null) {
                            callBack.onFailed(e.toString());
                        }
                    }

                    @Override
                    public void onNext(RepositoryInfo info) {
                        if (callBack != null) {
                            callBack.onSuccessfully(info);
                        }
                    }
                });
    }

    public Subscription getCommitsOfRepo(String owner, String repo, Map<String, String> params, IRepositoryCallBack<List<CommitInfo>> callBack) {
        return GitHubApi.getRepositoriesApi().getRepoCommits(owner, repo, params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CommitInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null) {
                            callBack.onFailed(e.toString());
                        }
                    }

                    @Override
                    public void onNext(List<CommitInfo> commitInfo) {
                        if (callBack != null) {
                            callBack.onSuccessfully(commitInfo);
                        }
                    }
                });
    }

    public Subscription getCommentsForACommit(String owner, String repo, String ref, Map<String, String> params, IRepositoryCallBack<List<CommentInfo>> callBack) {
        return GitHubApi.getRepositoriesApi().getCommentsForACommit(owner, repo, ref, params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CommentInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null) {
                            callBack.onFailed(e.toString());
                        }
                    }

                    @Override
                    public void onNext(List<CommentInfo> commentInfo) {
                        if (callBack != null) {
                            callBack.onSuccessfully(commentInfo);
                        }
                    }
                });
    }

    public Subscription createACommentForCommit(String owner, String repo, String sha, CommentParam param, String token, IRepositoryCallBack<CommentInfo> callBack) {
        return GitHubApi.getRepositoriesApi().createACommitComment(owner, repo, sha, param, token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommentInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null) {
                            callBack.onFailed(e.toString());
                        }
                    }

                    @Override
                    public void onNext(CommentInfo commentInfo) {
                        if (callBack != null) {
                            callBack.onSuccessfully(commentInfo);
                        }
                    }
                });
    }
}
