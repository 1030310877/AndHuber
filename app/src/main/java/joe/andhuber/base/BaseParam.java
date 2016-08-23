package joe.andhuber.base;

/**
 * Description
 * Created by chenqiao on 2016/8/23.
 */
public class BaseParam {

    private int page = 1;
    private int per_page = 10;
    private String access_token;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public BaseParam() {
    }
}
