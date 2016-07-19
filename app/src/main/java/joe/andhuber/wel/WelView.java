package joe.andhuber.wel;

import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public interface WelView {

    void startToLogin();

    void startToMain(UserInfo user);
}