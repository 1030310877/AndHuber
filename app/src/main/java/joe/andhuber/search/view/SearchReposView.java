package joe.andhuber.search.view;

import java.util.List;

import joe.andhuber.base.BaseView;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/26.
 */
public interface SearchReposView extends BaseView {
    void showRepositories(List<RepositoryInfo> repositories);

    void loadFinished(String text);

    void refreshFinish();

    void setPage(int page);
}
