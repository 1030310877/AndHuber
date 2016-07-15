package joe.githubapi.model.search;

import java.io.Serializable;
import java.util.List;

import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class SearchUserResult implements Serializable {

    /**
     * total_count : 4880
     * incomplete_results : false
     * items : [{"login":"android","id":30177,"avatar_url":"https://avatars.githubusercontent.com/u/30177?v=3","gravatar_id":"","url":"https://api.github.com/users/android","html_url":"https://github.com/android","followers_url":"https://api.github.com/users/android/followers","following_url":"https://api.github.com/users/android/following{/other_user}","gists_url":"https://api.github.com/users/android/gists{/gist_id}","starred_url":"https://api.github.com/users/android/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/android/subscriptions","organizations_url":"https://api.github.com/users/android/orgs","repos_url":"https://api.github.com/users/android/repos","events_url":"https://api.github.com/users/android/events{/privacy}","received_events_url":"https://api.github.com/users/android/received_events","type":"Organization","site_admin":false,"score":41.681313},{"login":"skritchz","id":2517846,"avatar_url":"https://avatars.githubusercontent.com/u/2517846?v=3","gravatar_id":"","url":"https://api.github.com/users/skritchz","html_url":"https://github.com/skritchz","followers_url":"https://api.github.com/users/skritchz/followers","following_url":"https://api.github.com/users/skritchz/following{/other_user}","gists_url":"https://api.github.com/users/skritchz/gists{/gist_id}","starred_url":"https://api.github.com/users/skritchz/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/skritchz/subscriptions","organizations_url":"https://api.github.com/users/skritchz/orgs","repos_url":"https://api.github.com/users/skritchz/repos","events_url":"https://api.github.com/users/skritchz/events{/privacy}","received_events_url":"https://api.github.com/users/skritchz/received_events","type":"User","site_admin":false,"score":32.51405}]
     */

    private int total_count;
    private boolean incomplete_results;
    /**
     * login : android
     * id : 30177
     * avatar_url : https://avatars.githubusercontent.com/u/30177?v=3
     * gravatar_id :
     * url : https://api.github.com/users/android
     * html_url : https://github.com/android
     * followers_url : https://api.github.com/users/android/followers
     * following_url : https://api.github.com/users/android/following{/other_user}
     * gists_url : https://api.github.com/users/android/gists{/gist_id}
     * starred_url : https://api.github.com/users/android/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/android/subscriptions
     * organizations_url : https://api.github.com/users/android/orgs
     * repos_url : https://api.github.com/users/android/repos
     * events_url : https://api.github.com/users/android/events{/privacy}
     * received_events_url : https://api.github.com/users/android/received_events
     * type : Organization
     * site_admin : false
     * score : 41.681313
     */

    private List<UserInfo> items;

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

    public List<UserInfo> getItems() {
        return items;
    }

    public void setItems(List<UserInfo> items) {
        this.items = items;
    }
}
