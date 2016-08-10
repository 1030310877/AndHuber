package joe.githubapi.model.repositories;

import java.io.Serializable;

import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/8/10.
 */
public class CommentInfo implements Serializable {

    /**
     * url : https://api.github.com/repos/1030310877/AndHuber/comments/18586409
     * html_url : https://github.com/1030310877/AndHuber/commit/fd38a2d3ab8d0a6090c8cbd112eb8c74f86e10d1#commitcomment-18586409
     * id : 18586409
     * user : null
     * position : 4
     * line : 14
     * path :
     * commit_id : fd38a2d3ab8d0a6090c8cbd112eb8c74f86e10d1
     * created_at : 2016-08-10T08:13:51Z
     * updated_at : 2016-08-10T08:13:51Z
     * body_html : <h1>测试一下Md格式是什么样的</h1>
     * <p>
     * <p><code>test</code></p>
     * body_text : 测试一下Md格式是什么样的
     * <p>
     * test
     * body : # 测试一下Md格式是什么样的
     * `test`
     */

    private String url;
    private String html_url;
    private int id;
    private UserInfo user;
    private int position;
    private int line;
    private String path;
    private String commit_id;
    private String created_at;
    private String updated_at;
    private String body_html;
    private String body_text;
    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCommit_id() {
        return commit_id;
    }

    public void setCommit_id(String commit_id) {
        this.commit_id = commit_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getBody_text() {
        return body_text;
    }

    public void setBody_text(String body_text) {
        this.body_text = body_text;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}