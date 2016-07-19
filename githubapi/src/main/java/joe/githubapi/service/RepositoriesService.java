package joe.githubapi.service;

import java.util.List;
import java.util.Map;

import joe.githubapi.model.repositories.ContentInfo;
import joe.githubapi.model.repositories.ReadMeInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public interface RepositoriesService {

    @GET("/user/repos")
    Observable<List<RepositoryInfo>> getNowUsersRepositories(@QueryMap Map<String, String> params);

    @GET("/users/{username}/repos")
    Observable<List<RepositoryInfo>> getUserRepositories(@Path("username") String username, @QueryMap Map<String, String> params);

    @GET("/repos/{owner}/{repo}/readme")
    Observable<ReadMeInfo> getReadMe(@Path("owner") String owner, @Path("repo") String repo, @Query("access_token") String token);

    @GET("/repos/{owner}/{repo}/readme")
    Observable<ResponseBody> getReadMeWithHeader(@Header("Accept") String accept, @Path("owner") String owner, @Path("repo") String repo, @Query("access_token") String token);

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<List<ContentInfo>> getRepoContent(@Path("owner") String owner, @Path("repo") String repo, @Path("path") String path, @Query("access_token") String token);

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<ResponseBody> getRepoFileContentWithHeader(@Header("Accept") String accept, @Path("owner") String owner, @Path("repo") String repo, @Path("path") String path, @Query("access_token") String token);
}
