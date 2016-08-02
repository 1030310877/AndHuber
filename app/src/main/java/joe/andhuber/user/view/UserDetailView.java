package joe.andhuber.user.view;

import joe.andhuber.base.BaseView;

/**
 * Description
 * Created by chenqiao on 2016/8/1.
 */
public interface UserDetailView extends BaseView {

    void setChanged(boolean changed);

    void setEditable(boolean isEnable);

    void showFollowers(int num);

    void showStarred(int num);

    void showFollowing(int num);

    void showHeadImg(String url);

    void showLoginName(String login);

    void showName(String name);

    void showEmail(String email);

    void showBlog(String blog);

    void showBio(String bio);

    void showCompany(String company);

    void showLocation(String location);

    void showHireable(boolean hireable);

    void showBtns(boolean isShown);

    void showEditImg(boolean isShown);
}
