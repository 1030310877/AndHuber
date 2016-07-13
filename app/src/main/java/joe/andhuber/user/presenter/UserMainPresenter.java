package joe.andhuber.user.presenter;

import joe.andhuber.user.view.RepositoryView;
import joe.andhuber.user.view.StarView;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface UserMainPresenter {

    void setStarView(StarView view);

    void setRepositoryView(RepositoryView view);

    void initInformation();

    void initUserViews(UserInfo userInfo);

    void getUserRepositories(String username);

    void getUserStars(String username);

    void refreshData(int nowIndex);
}