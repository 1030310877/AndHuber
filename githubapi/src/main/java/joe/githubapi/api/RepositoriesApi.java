package joe.githubapi.api;

import android.util.Base64;

import java.util.List;
import java.util.Map;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.model.repositories.CommentInfo;
import joe.githubapi.model.repositories.CommentParam;
import joe.githubapi.model.repositories.CommitInfo;
import joe.githubapi.model.repositories.ContentInfo;
import joe.githubapi.model.repositories.ForkParam;
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

    public Observable<ReadMeInfo> getReadMe(String owner, String repo, String token) {
        return createService().getReadMe(owner, repo, token);
    }

    public Observable<ResponseBody> getReadMeForHtml(String owner, String repo, String token) {
        return createService().getReadMeWithHeader("application/vnd.github.VERSION.html", owner, repo, token);
    }

    public Observable<ResponseBody> getReadMeForRaw(String owner, String repo, String token) {
        return createService().getReadMeWithHeader("application/vnd.github.VERSION.raw", owner, repo, token);
    }

    public Observable<List<ContentInfo>> getRepoContent(String owner, String repo, String path, String token) {
        return createService().getRepoContent(owner, repo, path, token);
    }

    public Observable<ResponseBody> getRepoFileContent(String url) {
        return createService().getRepoFileContent(url);
    }

    public Observable<ResponseBody> getRepoFileContentForHtml(String owner, String repo, String path, String token) {
        return createService().getRepoFileContentWithHeader("application/vnd.github.VERSION.html", owner, repo, path, token);
    }

    public Observable<ResponseBody> getRepoFileContentForRaw(String owner, String repo, String path, String token) {
        return createService().getRepoFileContentWithHeader("application/vnd.github.VERSION.raw", owner, repo, path, token);
    }

    public Observable<RepositoryInfo> forkRepository(String owner, String repo, ForkParam organization, String access_token) {
        return createService().forkRepository(owner, repo, organization, access_token);
    }

    public Observable<RepositoryInfo> forkRepositoryWithBasicAuth(String username, String password, String owner, String repo, ForkParam organization) {
        String credentials = username + ":" + password;
        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        basic = basic.trim();
        return createService().forkRepositoryWithBasicAuth(basic, owner, repo, organization);
    }

    public Observable<List<CommitInfo>> getRepoCommits(String owner, String repo, Map<String, String> params) {
        return createService().getRepoCommits(owner, repo, params);
    }

    public Observable<CommitInfo> getRepoCommitBySHA(String owner, String repo, String sha, String access_token) {
        return createService().getRepoCommitBySHA(owner, repo, sha, access_token);
    }

    public Observable<ResponseBody> getRepoCommitBySHAWithHeader(String accept, String owner, String repo, String sha, String access_token) {
        return createService().getRepoCommitBySHAWitHeader(accept, owner, repo, sha, access_token);
    }

    public Observable<List<CommentInfo>> getRepoComments(String owner, String repo, String access_token) {
        return createService().getRepoComments(owner, repo, access_token);
    }

    public Observable<List<CommentInfo>> getRepoCommentsWithHeader(String accept, String owner, String repo, String access_token) {
        return createService().getRepoCommentsWithHeader(accept, owner, repo, access_token);
    }

    public Observable<List<CommentInfo>> getCommentsForACommit(String owner, String repo, String ref, String access_token) {
        return createService().getCommentsForACommit(owner, repo, ref, access_token);
    }

    public Observable<CommentInfo> getAComment(String owner, String repo, String comment_id, String access_token) {
        return createService().getAComment(owner, repo, comment_id, access_token);
    }

    public Observable<CommentInfo> createACommitComment(String owner, String repo, String sha, CommentParam param, String access_token) {
        return createService().createACommitComment(owner, repo, sha, param, access_token);
    }

    public Observable<CommentInfo> updateACommitComment(String owner, String repo, String id, String body, String access_token) {
        CommentParam param = new CommentParam();
        param.setBody(body);
        return createService().updateACommitComment(owner, repo, id, param, access_token);
    }

    public Observable<ResponseBody> deleteACommitComment(String owner, String repo, String id, String access_token) {
        return createService().deleteACommitComment(owner, repo, id, access_token);
    }
}