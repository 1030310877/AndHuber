package joe.andhuber.repository.view;

import joe.andhuber.base.BaseView;

/**
 * Description
 * Created by chenqiao on 2016/8/23.
 */
public interface CommitDetailView extends BaseView {

    void setHead(String imgUrl);

    void setName(String name);

    void setCommitMsg(String msg);
}
