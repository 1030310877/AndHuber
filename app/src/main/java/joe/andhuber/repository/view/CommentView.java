package joe.andhuber.repository.view;

import java.util.List;

import joe.andhuber.base.BaseView;
import joe.githubapi.model.repositories.CommentInfo;

/**
 * Description
 * Created by chenqiao on 2016/9/2.
 */
public interface CommentView extends BaseView {

    void addComments(List<CommentInfo> infoList);

    void clearComments();

    void refreshFinish();

    void setPage(int page);

    void loadFinish(String text);
}
