package joe.andhuber.repository.view;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public interface RepoDetailView {

    void loadReadMe(String content);

    void showWaitDialog();

    void dismissWaitDialog();
}
