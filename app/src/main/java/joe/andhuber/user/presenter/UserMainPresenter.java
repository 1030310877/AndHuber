package joe.andhuber.user.presenter;

import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface UserMainPresenter {

    void initInformation();

    void initUserViews(UserInfo userInfo);

    void refreshData(int nowIndex);
}