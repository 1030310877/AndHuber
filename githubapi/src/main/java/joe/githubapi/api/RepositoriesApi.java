package joe.githubapi.api;

import java.util.List;
import java.util.Map;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.repositories.ReadMeInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.service.RepositoriesService;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class RepositoriesApi {

    private RepositoriesService createService() {
        return GitHubApi.createService(RepositoriesService.class);
    }

    public Observable<List<RepositoryInfo>> getNowUsersRepositories(Map<String, String> params) {
        return createService().getNowUsersRepositories(params);
    }

    public Observable<List<RepositoryInfo>> getUserRepositories(String username, Map<String, String> params) {
        return createService().getUserRepositories(username, params);
    }

    public Observable<ReadMeInfo> getReadMe(String owner, String repo) {
        return createService().getReadMe(owner, repo);
    }

    public Observable<ResponseBody> getReadMeForHtml(String owner, String repo) {
        return createService().getReadMeWithHeader("application/vnd.github.VERSION.html", owner, repo);
    }
}
