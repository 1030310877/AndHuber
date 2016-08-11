package joe.githubapi.model.repositories;

import java.io.Serializable;

/**
 * Description
 * Created by chenqiao on 2016/8/11.
 */
public class ForkParam implements Serializable {
    private String organization;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
