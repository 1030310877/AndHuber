package joe.andhuber.main.view;

import java.util.List;

import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface RepositoryView {

    void showRepositories(List<RepositoryInfo> data);

    void clearRepositories();
}
