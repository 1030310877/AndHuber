package joe.andhuber.repository.presenter;

import joe.andhuber.HuberApplication;
import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.model.activity.ActivityModel;
import joe.andhuber.model.activity.IActivityCallBack;
import joe.andhuber.model.repository.IRepositoryCallBack;
import joe.andhuber.model.repository.RepositoryModel;
import joe.andhuber.repository.view.RepoDetailView;
import joe.andhuber.utils.ToastUtil;
import joe.githubapi.model.activity.SubscriptionParam;
import joe.githubapi.model.repositories.RepositoryInfo;
import rx.Subscription;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public class RepoDetailPresenterImpl implements RepoDetailPresenter {

    private RepoDetailView view;
    private ActivityModel activityModel;
    private RepositoryModel repositoryModel;
    private Subscription starSubscription;
    private Subscription watchSubscription;
    private Subscription forkSubscription;

    public RepoDetailPresenterImpl(RepoDetailView view) {
        this.view = view;
        activityModel = new ActivityModel();
        repositoryModel = new RepositoryModel();
    }

    @Override
    public void checkIsStarred(String owner, String repo) {
        activityModel.isStarringRepo(owner, repo, UserConfig.getInstance().getToken(), new IActivityCallBack<Boolean>() {
            @Override
            public void onSuccessfully(Boolean result) {
                view.setIsStarred(result);
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void starRepository(String owner, String repo) {
        if (starSubscription != null) {
            starSubscription.unsubscribe();
            starSubscription = null;
        }
        starSubscription = activityModel.starRepository(owner, repo, UserConfig.getInstance().getToken(), new IActivityCallBack<Boolean>() {
            @Override
            public void onSuccessfully(Boolean result) {
                if (result) {
                    view.setIsStarred(true);
                    ToastUtil.show(HuberApplication.getInstance(), R.string.star_success);
                } else {
                    ToastUtil.show(HuberApplication.getInstance(), R.string.star_failed);
                }
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void unStarRepository(String owner, String repo) {
        if (starSubscription != null) {
            starSubscription.unsubscribe();
            starSubscription = null;
        }
        starSubscription = activityModel.unStarRepository(owner, repo, UserConfig.getInstance().getToken(), new IActivityCallBack<Boolean>() {
            @Override
            public void onSuccessfully(Boolean result) {
                if (result) {
                    view.setIsStarred(false);
                } else {
                    ToastUtil.show(HuberApplication.getInstance(), R.string.undo_star_failed);
                }
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void watchRepository(String owner, String repo) {
        if (watchSubscription != null) {
            watchSubscription.unsubscribe();
            watchSubscription = null;
        }
        SubscriptionParam param = new SubscriptionParam();
        param.setSubscribed(true);
        param.setIgnored(false);
        watchSubscription = activityModel.setASubscription(owner, repo, param, UserConfig.getInstance().getToken(), new IActivityCallBack<Boolean>() {
            @Override
            public void onSuccessfully(Boolean result) {
                view.setIsWatched(result);
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void unWatchRepository(String owner, String repo) {
        if (watchSubscription != null) {
            watchSubscription.unsubscribe();
            watchSubscription = null;
        }
        watchSubscription = activityModel.deleteASubscription(owner, repo, UserConfig.getInstance().getToken(), new IActivityCallBack<Boolean>() {
            @Override
            public void onSuccessfully(Boolean result) {
                view.setIsWatched(false);
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void forkRepository(String owner, String repo, String organization) {
        if (forkSubscription != null) {
            forkSubscription.unsubscribe();
            forkSubscription = null;
        }
        forkSubscription = repositoryModel.forkRepository(owner, repo, organization, UserConfig.getInstance().getToken(), new IRepositoryCallBack<RepositoryInfo>() {
            @Override
            public void onSuccessfully(RepositoryInfo result) {
                view.showForks(result.getParent().getForks_count());
                ToastUtil.show(HuberApplication.getInstance(), R.string.fork_success);
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }

    @Override
    public void checkIsWatched(String owner, String repo) {
        activityModel.getASubscription(owner, repo, UserConfig.getInstance().getToken(), new IActivityCallBack<Boolean>() {
            @Override
            public void onSuccessfully(Boolean result) {
                view.setIsWatched(result);
            }

            @Override
            public void onFailed(String message) {
                ToastUtil.show(HuberApplication.getInstance(), message);
            }
        });
    }
}