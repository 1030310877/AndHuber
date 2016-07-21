package joe.andhuber.repository.view;

import joe.andhuber.base.BaseView;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public interface FileContentView extends BaseView{

    void showReadMe(String content);

    void showCode(String code);
}
