package joe.githubapi.model.search;

import java.util.List;

import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class SearchRepoResult {

    /**
     * total_count : 368070
     * incomplete_results : false
     * items : [{"id":5550567,"name":"android","full_name":"owncloud/android","owner":{"login":"owncloud","id":1645051,"avatar_url":"https://avatars.githubusercontent.com/u/1645051?v=3","gravatar_id":"","url":"https://api.github.com/users/owncloud","html_url":"https://github.com/owncloud","followers_url":"https://api.github.com/users/owncloud/followers","following_url":"https://api.github.com/users/owncloud/following{/other_user}","gists_url":"https://api.github.com/users/owncloud/gists{/gist_id}","starred_url":"https://api.github.com/users/owncloud/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/owncloud/subscriptions","organizations_url":"https://api.github.com/users/owncloud/orgs","repos_url":"https://api.github.com/users/owncloud/repos","events_url":"https://api.github.com/users/owncloud/events{/privacy}","received_events_url":"https://api.github.com/users/owncloud/received_events","type":"Organization","site_admin":false},"private":false,"html_url":"https://github.com/owncloud/android","description":":phone: The ownCloud Android App","fork":false,"url":"https://api.github.com/repos/owncloud/android","forks_url":"https://api.github.com/repos/owncloud/android/forks","keys_url":"https://api.github.com/repos/owncloud/android/keys{/key_id}","collaborators_url":"https://api.github.com/repos/owncloud/android/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/owncloud/android/teams","hooks_url":"https://api.github.com/repos/owncloud/android/hooks","issue_events_url":"https://api.github.com/repos/owncloud/android/issues/events{/number}","events_url":"https://api.github.com/repos/owncloud/android/events","assignees_url":"https://api.github.com/repos/owncloud/android/assignees{/user}","branches_url":"https://api.github.com/repos/owncloud/android/branches{/branch}","tags_url":"https://api.github.com/repos/owncloud/android/tags","blobs_url":"https://api.github.com/repos/owncloud/android/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/owncloud/android/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/owncloud/android/git/refs{/sha}","trees_url":"https://api.github.com/repos/owncloud/android/git/trees{/sha}","statuses_url":"https://api.github.com/repos/owncloud/android/statuses/{sha}","languages_url":"https://api.github.com/repos/owncloud/android/languages","stargazers_url":"https://api.github.com/repos/owncloud/android/stargazers","contributors_url":"https://api.github.com/repos/owncloud/android/contributors","subscribers_url":"https://api.github.com/repos/owncloud/android/subscribers","subscription_url":"https://api.github.com/repos/owncloud/android/subscription","commits_url":"https://api.github.com/repos/owncloud/android/commits{/sha}","git_commits_url":"https://api.github.com/repos/owncloud/android/git/commits{/sha}","comments_url":"https://api.github.com/repos/owncloud/android/comments{/number}","issue_comment_url":"https://api.github.com/repos/owncloud/android/issues/comments{/number}","contents_url":"https://api.github.com/repos/owncloud/android/contents/{+path}","compare_url":"https://api.github.com/repos/owncloud/android/compare/{base}...{head}","merges_url":"https://api.github.com/repos/owncloud/android/merges","archive_url":"https://api.github.com/repos/owncloud/android/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/owncloud/android/downloads","issues_url":"https://api.github.com/repos/owncloud/android/issues{/number}","pulls_url":"https://api.github.com/repos/owncloud/android/pulls{/number}","milestones_url":"https://api.github.com/repos/owncloud/android/milestones{/number}","notifications_url":"https://api.github.com/repos/owncloud/android/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/owncloud/android/labels{/name}","releases_url":"https://api.github.com/repos/owncloud/android/releases{/id}","deployments_url":"https://api.github.com/repos/owncloud/android/deployments","created_at":"2012-08-25T10:35:56Z","updated_at":"2016-07-14T06:04:52Z","pushed_at":"2016-07-14T00:43:53Z","git_url":"git://github.com/owncloud/android.git","ssh_url":"git@github.com:owncloud/android.git","clone_url":"https://github.com/owncloud/android.git","svn_url":"https://github.com/owncloud/android","homepage":"","size":186546,"stargazers_count":1669,"watchers_count":1669,"language":"Java","has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":false,"forks_count":1911,"mirror_url":null,"open_issues_count":361,"forks":1911,"open_issues":361,"watchers":1669,"default_branch":"master","score":82.084},{"id":321360,"name":"android","full_name":"uavana/android","owner":{"login":"uavana","id":132860,"avatar_url":"https://avatars.githubusercontent.com/u/132860?v=3","gravatar_id":"","url":"https://api.github.com/users/uavana","html_url":"https://github.com/uavana","followers_url":"https://api.github.com/users/uavana/followers","following_url":"https://api.github.com/users/uavana/following{/other_user}","gists_url":"https://api.github.com/users/uavana/gists{/gist_id}","starred_url":"https://api.github.com/users/uavana/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/uavana/subscriptions","organizations_url":"https://api.github.com/users/uavana/orgs","repos_url":"https://api.github.com/users/uavana/repos","events_url":"https://api.github.com/users/uavana/events{/privacy}","received_events_url":"https://api.github.com/users/uavana/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/uavana/android","description":"Misc Android stuff","fork":false,"url":"https://api.github.com/repos/uavana/android","forks_url":"https://api.github.com/repos/uavana/android/forks","keys_url":"https://api.github.com/repos/uavana/android/keys{/key_id}","collaborators_url":"https://api.github.com/repos/uavana/android/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/uavana/android/teams","hooks_url":"https://api.github.com/repos/uavana/android/hooks","issue_events_url":"https://api.github.com/repos/uavana/android/issues/events{/number}","events_url":"https://api.github.com/repos/uavana/android/events","assignees_url":"https://api.github.com/repos/uavana/android/assignees{/user}","branches_url":"https://api.github.com/repos/uavana/android/branches{/branch}","tags_url":"https://api.github.com/repos/uavana/android/tags","blobs_url":"https://api.github.com/repos/uavana/android/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/uavana/android/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/uavana/android/git/refs{/sha}","trees_url":"https://api.github.com/repos/uavana/android/git/trees{/sha}","statuses_url":"https://api.github.com/repos/uavana/android/statuses/{sha}","languages_url":"https://api.github.com/repos/uavana/android/languages","stargazers_url":"https://api.github.com/repos/uavana/android/stargazers","contributors_url":"https://api.github.com/repos/uavana/android/contributors","subscribers_url":"https://api.github.com/repos/uavana/android/subscribers","subscription_url":"https://api.github.com/repos/uavana/android/subscription","commits_url":"https://api.github.com/repos/uavana/android/commits{/sha}","git_commits_url":"https://api.github.com/repos/uavana/android/git/commits{/sha}","comments_url":"https://api.github.com/repos/uavana/android/comments{/number}","issue_comment_url":"https://api.github.com/repos/uavana/android/issues/comments{/number}","contents_url":"https://api.github.com/repos/uavana/android/contents/{+path}","compare_url":"https://api.github.com/repos/uavana/android/compare/{base}...{head}","merges_url":"https://api.github.com/repos/uavana/android/merges","archive_url":"https://api.github.com/repos/uavana/android/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/uavana/android/downloads","issues_url":"https://api.github.com/repos/uavana/android/issues{/number}","pulls_url":"https://api.github.com/repos/uavana/android/pulls{/number}","milestones_url":"https://api.github.com/repos/uavana/android/milestones{/number}","notifications_url":"https://api.github.com/repos/uavana/android/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/uavana/android/labels{/name}","releases_url":"https://api.github.com/repos/uavana/android/releases{/id}","deployments_url":"https://api.github.com/repos/uavana/android/deployments","created_at":"2009-09-29T18:41:28Z","updated_at":"2016-07-05T22:15:31Z","pushed_at":"2015-09-28T12:06:03Z","git_url":"git://github.com/uavana/android.git","ssh_url":"git@github.com:uavana/android.git","clone_url":"https://github.com/uavana/android.git","svn_url":"https://github.com/uavana/android","homepage":"","size":148,"stargazers_count":24,"watchers_count":24,"language":null,"has_issues":false,"has_downloads":true,"has_wiki":true,"has_pages":false,"forks_count":2570,"mirror_url":null,"open_issues_count":1,"forks":2570,"open_issues":1,"watchers":24,"default_branch":"master","score":81.964455}]
     */

    private int total_count;
    private boolean incomplete_results;
    /**
     * id : 5550567
     * name : android
     * full_name : owncloud/android
     * owner : {"login":"owncloud","id":1645051,"avatar_url":"https://avatars.githubusercontent.com/u/1645051?v=3","gravatar_id":"","url":"https://api.github.com/users/owncloud","html_url":"https://github.com/owncloud","followers_url":"https://api.github.com/users/owncloud/followers","following_url":"https://api.github.com/users/owncloud/following{/other_user}","gists_url":"https://api.github.com/users/owncloud/gists{/gist_id}","starred_url":"https://api.github.com/users/owncloud/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/owncloud/subscriptions","organizations_url":"https://api.github.com/users/owncloud/orgs","repos_url":"https://api.github.com/users/owncloud/repos","events_url":"https://api.github.com/users/owncloud/events{/privacy}","received_events_url":"https://api.github.com/users/owncloud/received_events","type":"Organization","site_admin":false}
     * private : false
     * html_url : https://github.com/owncloud/android
     * description : :phone: The ownCloud Android App
     * fork : false
     * url : https://api.github.com/repos/owncloud/android
     * forks_url : https://api.github.com/repos/owncloud/android/forks
     * keys_url : https://api.github.com/repos/owncloud/android/keys{/key_id}
     * collaborators_url : https://api.github.com/repos/owncloud/android/collaborators{/collaborator}
     * teams_url : https://api.github.com/repos/owncloud/android/teams
     * hooks_url : https://api.github.com/repos/owncloud/android/hooks
     * issue_events_url : https://api.github.com/repos/owncloud/android/issues/events{/number}
     * events_url : https://api.github.com/repos/owncloud/android/events
     * assignees_url : https://api.github.com/repos/owncloud/android/assignees{/user}
     * branches_url : https://api.github.com/repos/owncloud/android/branches{/branch}
     * tags_url : https://api.github.com/repos/owncloud/android/tags
     * blobs_url : https://api.github.com/repos/owncloud/android/git/blobs{/sha}
     * git_tags_url : https://api.github.com/repos/owncloud/android/git/tags{/sha}
     * git_refs_url : https://api.github.com/repos/owncloud/android/git/refs{/sha}
     * trees_url : https://api.github.com/repos/owncloud/android/git/trees{/sha}
     * statuses_url : https://api.github.com/repos/owncloud/android/statuses/{sha}
     * languages_url : https://api.github.com/repos/owncloud/android/languages
     * stargazers_url : https://api.github.com/repos/owncloud/android/stargazers
     * contributors_url : https://api.github.com/repos/owncloud/android/contributors
     * subscribers_url : https://api.github.com/repos/owncloud/android/subscribers
     * subscription_url : https://api.github.com/repos/owncloud/android/subscription
     * commits_url : https://api.github.com/repos/owncloud/android/commits{/sha}
     * git_commits_url : https://api.github.com/repos/owncloud/android/git/commits{/sha}
     * comments_url : https://api.github.com/repos/owncloud/android/comments{/number}
     * issue_comment_url : https://api.github.com/repos/owncloud/android/issues/comments{/number}
     * contents_url : https://api.github.com/repos/owncloud/android/contents/{+path}
     * compare_url : https://api.github.com/repos/owncloud/android/compare/{base}...{head}
     * merges_url : https://api.github.com/repos/owncloud/android/merges
     * archive_url : https://api.github.com/repos/owncloud/android/{archive_format}{/ref}
     * downloads_url : https://api.github.com/repos/owncloud/android/downloads
     * issues_url : https://api.github.com/repos/owncloud/android/issues{/number}
     * pulls_url : https://api.github.com/repos/owncloud/android/pulls{/number}
     * milestones_url : https://api.github.com/repos/owncloud/android/milestones{/number}
     * notifications_url : https://api.github.com/repos/owncloud/android/notifications{?since,all,participating}
     * labels_url : https://api.github.com/repos/owncloud/android/labels{/name}
     * releases_url : https://api.github.com/repos/owncloud/android/releases{/id}
     * deployments_url : https://api.github.com/repos/owncloud/android/deployments
     * created_at : 2012-08-25T10:35:56Z
     * updated_at : 2016-07-14T06:04:52Z
     * pushed_at : 2016-07-14T00:43:53Z
     * git_url : git://github.com/owncloud/android.git
     * ssh_url : git@github.com:owncloud/android.git
     * clone_url : https://github.com/owncloud/android.git
     * svn_url : https://github.com/owncloud/android
     * homepage :
     * size : 186546
     * stargazers_count : 1669
     * watchers_count : 1669
     * language : Java
     * has_issues : true
     * has_downloads : true
     * has_wiki : true
     * has_pages : false
     * forks_count : 1911
     * mirror_url : null
     * open_issues_count : 361
     * forks : 1911
     * open_issues : 361
     * watchers : 1669
     * default_branch : master
     * score : 82.084
     */

    private List<RepositoryInfo> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<RepositoryInfo> getItems() {
        return items;
    }

    public void setItems(List<RepositoryInfo> items) {
        this.items = items;
    }
}
