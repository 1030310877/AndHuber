package joe.andhuber.model.repository;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class RepositoryParams {
    private String visibility = "all";
    private String affiliation = "owner,collaborator,organization_member";
    private String type;
    private String sort = "full_name";
    private String direction = "asc";
    private String access_token = "";
    private String page = "1";
    private String per_page = "10";

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String paer_page) {
        this.per_page = paer_page;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public RepositoryParams() {
    }
}
