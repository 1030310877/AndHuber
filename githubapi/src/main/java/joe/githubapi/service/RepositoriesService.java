package joe.githubapi.service;

import java.util.List;
import java.util.Map;

import joe.githubapi.model.repositories.RepositoryInfo;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface RepositoriesService {

    @GET("/user/repos")
    Observable<List<RepositoryInfo>> getNowUsersRepositories(@QueryMap Map<String, String> params);
}
