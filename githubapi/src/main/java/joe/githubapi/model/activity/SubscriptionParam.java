package joe.githubapi.model.activity;

/**
 * Description
 * Created by chenqiao on 2016/7/29.
 */
public class SubscriptionParam {

    private boolean subscribed = true;
    private boolean ignored = false;

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
}
