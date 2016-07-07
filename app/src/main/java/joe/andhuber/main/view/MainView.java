package joe.andhuber.main.view;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface MainView {

    void setHeadImg(Bitmap bitmap);

    void setHeadImg(@DrawableRes int resId);

    void setHeadImg(String url);

    void setTitle(String title);

    void showWaitDialog();

    void dismissWaitDialog();
}