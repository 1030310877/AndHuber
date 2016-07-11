package joe.githubapi.api;

import java.util.List;
import java.util.Map;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.service.ActivityService;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public class ActivityApi {

    public Observable<List<RepositoryInfo>> getNowUsersStars(Map<String, String> params) {
        return GitHubApi.createService(ActivityService.class).getNowUsersStars(params);
    }
}
