package joe.andhuber.user.presenter;

import android.content.Intent;

import joe.githubapi.model.user.UserInfo;
import joe.githubapi.model.user.UserParam;

/**
 * Description
 * Created by chenqiao on 2016/8/1.
 */
public interface UserDetailPresenter {

    UserInfo getUserInfo();

    void getUserInfo(Intent intent);

    void showInfo(UserInfo info);

    void saveUser(UserParam param);

    void initInfo();
}
