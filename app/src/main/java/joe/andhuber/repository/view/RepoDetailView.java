package joe.andhuber.repository.view;

import java.util.List;

import joe.githubapi.model.repositories.ContentInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public interface RepoDetailView {

    void showWaitDialog();

    void dismissWaitDialog();

    void showPath(List<String> paths);

    void showFiles(List<ContentInfo> files);

    void showStar(int num, boolean isStarred);

    void showFork(int num, boolean isForked);

    void startToContentView(String fileName, String content, int type);
}
