package joe.andhuber.repository.view;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public interface FileContentView {

    void showReadMe(String content);

    void showCode(String code);

    void showWaitDialog();

    void dismissWaitDialog();
}
