package joe.andhuber.main.presenter;

import joe.andhuber.main.view.RepositoryView;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface MainPresenter {

    void setRepositoryView(RepositoryView view);

    void initInformation();

    void initUserViews(UserInfo userInfo);

    void getUsersRepositories();
}