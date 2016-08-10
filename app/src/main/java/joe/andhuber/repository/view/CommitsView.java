package joe.andhuber.repository.view;

import java.util.List;

import joe.andhuber.base.BaseView;
import joe.githubapi.model.repositories.CommitInfo;

/**
 * Description
 * Created by chenqiao on 2016/8/9.
 */
public interface CommitsView extends BaseView {

    void addCommits(List<CommitInfo> infoList);

    void clearCommits();

    void refreshFinish();

    void setPage(int page);

    void loadFinish(String text);
}
