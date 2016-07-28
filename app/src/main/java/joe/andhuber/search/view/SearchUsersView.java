package joe.andhuber.search.view;

import java.util.List;

import joe.andhuber.base.BaseView;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/21.
 */
public interface SearchUsersView extends BaseView {

    void showUsers(List<UserInfo> users);

    void loadFinished(String text);

    void refreshFinish();

    void setPage(int page);

    void setTotal(int total);
}
