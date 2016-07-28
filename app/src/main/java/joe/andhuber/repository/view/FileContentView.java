package joe.andhuber.repository.view;

import joe.andhuber.base.BaseView;

/**
 * Description
 * Created by chenqiao on 2016/7/27.
 */
public interface FileContentView extends BaseView {

    void showPicture(String url);

    void showCode(String code);

    void showText(String content);
}