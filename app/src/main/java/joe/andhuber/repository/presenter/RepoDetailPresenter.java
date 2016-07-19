package joe.andhuber.repository.presenter;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public interface RepoDetailPresenter {

    void getReadMe(String owner, String repo);

    void getFilesByPath(String owner, String repo, String path);

    void getFileContentByPathForRaw(String owner, String repo, String path);
}
