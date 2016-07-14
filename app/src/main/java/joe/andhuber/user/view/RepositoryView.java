package joe.andhuber.user.view;

import java.util.List;

import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface RepositoryView {
    void addRepositories(List<RepositoryInfo> data);

    void clearRepositories();

    void setPage(int page);

    void loadFinish(String text);

    void showWaitDialog();

    void dismissWaitDialog();

    void moveToTop();
}
