package joe.andhuber.user.view;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface UserMainView {

    void setHeadImg(Bitmap bitmap);

    void setHeadImg(@DrawableRes int resId);

    void setHeadImg(String url);

    void setTitle(String title);

    void showWaitDialog();

    void dismissWaitDialog();

    void setBlog(String blogUrl);

    void setCompany(String company);

    void setEmail(String email);

    void setFollower(int num);

    void setFollowing(int num);
}