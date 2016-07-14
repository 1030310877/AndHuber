package joe.andhuber.model.activity;

/**
 * Description
 * Created by chenqiao on 2016/7/8.
 */
public class StarParams {

    private String sort = "created";
    private String direction = "desc";
    private String access_token = "";
    private int page = 1;
    private int per_page = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int paer_page) {
        this.per_page = paer_page;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public StarParams() {
    }
}