package joe.andhuber.repository.presenter;

import joe.andhuber.model.repository.CommitParams;

/**
 * Description
 * Created by chenqiao on 2016/8/9.
 */
public interface CommitsPresenter {

    void getCommitsOfRepo(String owner, String repo, CommitParams params);
}