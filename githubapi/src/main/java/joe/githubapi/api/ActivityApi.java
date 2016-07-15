package joe.githubapi.api;

import java.util.List;
import java.util.Map;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.event.EventInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.service.ActivityService;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public class ActivityApi {

    private ActivityService createService() {
        return GitHubApi.createService(ActivityService.class);
    }

    public Observable<List<RepositoryInfo>> getNowUsersStars(Map<String, String> params) {
        return createService().getNowUsersStars(params);
    }

    public Observable<List<RepositoryInfo>> getUserStars(String username, Map<String, String> params) {
        return createService().getUserStars(username, params);
    }

    public Observable<List<EventInfo>> getUsersEvents(String username, Map<String, String> params) {
        return createService().getUsersReceivedEvents(username, params);
    }

}
