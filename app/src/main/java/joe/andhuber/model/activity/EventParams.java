package joe.andhuber.model.activity;

/**
 * Description
 * Created by chenqiao on 2016/7/11.
 */
public class EventParams {
    private String access_token = "";
    private String page = "1";
    private String per_page = "10";

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public EventParams() {
    }
}
