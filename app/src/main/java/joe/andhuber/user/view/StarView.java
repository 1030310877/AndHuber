package joe.andhuber.user.view;

import java.util.List;

import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public interface StarView {
    void showStars(List<RepositoryInfo> data);

    void clearStars();
}
