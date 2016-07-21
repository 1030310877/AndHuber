package joe.andhuber.user.view;

import java.util.List;

import joe.andhuber.base.BaseView;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface RepositoryView extends BaseView {
    void addRepositories(List<RepositoryInfo> data);

    void clearRepositories();

    void setPage(int page);

    void loadFinish(String text);

    void moveToTop();
}
