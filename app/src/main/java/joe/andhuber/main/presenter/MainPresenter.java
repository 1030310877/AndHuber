package joe.andhuber.main.presenter;

import joe.andhuber.main.view.EventView;
import joe.andhuber.main.view.RepositoryView;
import joe.andhuber.main.view.StarView;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface MainPresenter {

    void setStarView(StarView view);

    void setRepositoryView(RepositoryView view);

    void setEventView(EventView view);

    void initInformation();

    void initUserViews(UserInfo userInfo);

    void getUsersRepositories();

    void getUsersStars();

    void getUsersEvents(String username);

    void refreshData(int nowIndex);
}