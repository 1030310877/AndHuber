package joe.andhuber.repository.presenter;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public interface RepoDetailPresenter {

    void checkIsStarred(String owner, String repo);

    void starRepository(String owner, String repo);

    void unStarRepository(String owner, String repo);

    void watchRepository(String owner, String repo);

    void unWatchRepository(String owner, String repo);

    void checkIsWatched(String owner, String repo);
}
