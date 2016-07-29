package joe.githubapi.model.activity;

/**
 * Description
 * Created by chenqiao on 2016/7/29.
 */
public class SubscriptionInfo {

    /**
     * subscribed : true
     * ignored : false
     * reason : null
     * created_at : 2012-10-06T21:34:12Z
     * url : https://api.github.com/repos/octocat/example/subscription
     * repository_url : https://api.github.com/repos/octocat/example
     */

    private boolean subscribed;
    private boolean ignored;
    private String reason;
    private String created_at;
    private String url;
    private String repository_url;

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public boolean isIgnored() {
        return ignored;
    }

    public void setIgnored(boolean ignored) {
        this.ignored = ignored;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }
}
