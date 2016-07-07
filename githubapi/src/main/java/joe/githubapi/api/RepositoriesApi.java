package joe.githubapi.api;

import java.util.List;
import java.util.Map;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.service.RepositoriesService;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class RepositoriesApi {

    public Observable<List<RepositoryInfo>> getNowUsersRepositories(Map<String, String> params) {
        return GitHubApi.createService(RepositoriesService.class).getNowUsersRepositories(params);
    }
}
