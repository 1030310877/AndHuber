package joe.andhuber.repository.presenter;

/**
 * Description
 * Created by chenqiao on 2016/7/28.
 */
public interface FileListPresenter {
    void getFilesByPath(String owner, String repo, String path);
}
