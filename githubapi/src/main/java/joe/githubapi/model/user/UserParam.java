package joe.githubapi.model.user;

/**
 * Description
 * Created by chenqiao on 2016/7/29.
 */
public class UserParam {

    /**
     * name : monalisa octocat
     * email : octocat@github.com
     * blog : https://github.com/blog
     * company : GitHub
     * location : San Francisco
     * hireable : true
     * bio : There once...
     */

    private String name;
    private String email;
    private String blog;
    private String company;
    private String location;
    private boolean hireable;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isHireable() {
        return hireable;
    }

    public void setHireable(boolean hireable) {
        this.hireable = hireable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
