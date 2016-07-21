package joe.andhuber.user.view;

import java.util.List;

import joe.andhuber.base.BaseView;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public interface StarView extends BaseView {
    void addStars(List<RepositoryInfo> data);

    void clearStars();

    void setPage(int page);

    void loadFinish(String text);

    void moveToTop();
}
