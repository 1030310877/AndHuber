package joe.andhuber.user.presenter;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface UserMainPresenter {

    void getUserInfo(String loginName);

    void refreshData(int nowIndex);
}