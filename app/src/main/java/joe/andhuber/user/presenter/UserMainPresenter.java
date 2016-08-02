package joe.andhuber.user.presenter;

import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface UserMainPresenter {
    UserInfo getUserInfo();

    void setUserInfo(UserInfo userInfo);

    void getUserInfoFromServer(String loginName);

    void refreshData(int nowIndex);

    void initUserViews(UserInfo userInfo);
}