package joe.andhuber.repository.presenter;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public interface RepoDetailPresenter {

    void getReadMe(String owner, String repo);

    void checkIsStarred(String owner, String repo);

    void starRepository(String owner, String repo);

    void unStarRepository(String owner, String repo);
}
