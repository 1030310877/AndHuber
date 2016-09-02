package joe.andhuber.repository.presenter;

import joe.andhuber.base.BaseParam;

/**
 * Description
 * Created by chenqiao on 2016/9/2.
 */
public interface CommentPresenter {
    void getCommentsForACommit(String owner, String repo, String ref, BaseParam param);
}
