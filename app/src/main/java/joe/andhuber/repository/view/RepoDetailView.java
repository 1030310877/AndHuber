package joe.andhuber.repository.view;

import joe.andhuber.base.BaseView;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public interface RepoDetailView extends BaseView {

    void showStars(int count);

    void setIsStarred(boolean isStarred);

    void showWatchers(int count);

    void setIsWatched(boolean isWatched);

    void showForks(int count);
}