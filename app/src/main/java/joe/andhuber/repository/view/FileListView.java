package joe.andhuber.repository.view;

import java.util.List;

import joe.andhuber.base.BaseView;
import joe.githubapi.model.repositories.ContentInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/28.
 */
public interface FileListView extends BaseView {

    void showPath(List<String> paths);

    void showFiles(List<ContentInfo> files);

    void startToContentView(String fileName, String url, String content);
}
