package joe.githubapi.service;

import java.util.List;
import java.util.Map;

import joe.githubapi.model.repositories.CommentInfo;
import joe.githubapi.model.repositories.CommitInfo;
import joe.githubapi.model.repositories.ContentInfo;
import joe.githubapi.model.repositories.ReadMeInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
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

    @GET
    Observable<ResponseBody> getRepoFileContent(@Url String url);

    @GET("/repos/{owner}/{repo}/contents/{path}")
    Observable<ResponseBody> getRepoFileContentWithHeader(@Header("Accept") String accept, @Path("owner") String owner, @Path("repo") String repo, @Path("path") String path, @Query("access_token") String token);

    @POST("/repos/{owner}/{repo}/forks")
    Observable<RepositoryInfo> forkRepository(@Path("owner") String owner, @Path("repo") String repo, @Query("organization") String organization, @Query("access_token") String token);

    @GET("/repos/{owner}/{repo}/commits")
    Observable<List<CommitInfo>> getRepoCommits(@Path("owner") String owner, @Path("repo") String repo, @QueryMap Map<String, String> params);

    @GET("/repos/{owner}/{repo}/commits/{sha}")
    Observable<CommitInfo> getRepoCommitBySHA(@Path("owner") String owner, @Path("repo") String repo, @Path("sha") String sha, @Query("access_token") String token);

    @GET("/repos/{owner}/{repo}/commits/{sha}")
    Observable<ResponseBody> getRepoCommitBySHAWitHeader(@Header("Accept") String accept, @Path("repo") String repo, @Path("sha") String sha, @Query("access_token") String token);

    @GET("/repo/{owner}/{repo}/comments")
    @Headers({
            "Accept : application/vnd.github-commitcomment.full+json"
    })
    Observable<List<CommentInfo>> getRepoComments(@Path("owner") String owner, @Path("repo") String repo, @Query("access_token") String token);

    @GET("/repo/{owner}/{repo}/comments")
    Observable<List<CommentInfo>> getRepoComments(@Header("Accept") String media_type, @Path("owner") String owner, @Path("repo") String repo, @Query("access_token") String token);

    @GET("/repo/{owner}/{repo}/commits/{sha}/comments")
    @Headers({
            "Accept : application/vnd.github-commitcomment.full+json"
    })
    Observable<List<CommentInfo>> getCommentsForACommit(@Path("owner") String owner, @Path("repo") String repo, @Path("sha") String ref, @Query("access_token") String token);
}