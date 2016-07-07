package joe.andhuber.main.model;

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
