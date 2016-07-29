package joe.githubapi.model.authentication;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 * Created by chenqiao on 2016/7/5.
 */
public class AuthenticationInfo implements Serializable {


    /**
     * id : 38830955
     * url : https://api.github.com/authorizations/38830955
     * app : {"name":"AndHuber","url":"https://github.com/1030310877","client_id":"f2f81a9ab54e23992bc3"}
     * token : 2a82ab954d8d93cd2d1706c8eeff409f7c7705c8
     * hashed_token : 1004a99961d27a7b1871978fd0eaa2c862a635c9b8cda348fc765a1e7e273e0e
     * token_last_eight : 7c7705c8
     * note : this is test
     * note_url : null
     * created_at : 2016-07-05T00:58:55Z
     * updated_at : 2016-07-05T00:58:55Z
     * scopes : []
     * fingerprint : ssss
     */

    private String id;
    private String url;
    /**
     * name : AndHuber
     * url : https://github.com/1030310877
     * client_id : f2f81a9ab54e23992bc3
     */

    private AppBean app;
    private String token;
    private String hashed_token;
    private String token_last_eight;
    private String note;
    private String note_url;
    private String created_at;
    private String updated_at;
    private String fingerprint;
    private List<String> scopes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHashed_token() {
        return hashed_token;
    }

    public void setHashed_token(String hashed_token) {
        this.hashed_token = hashed_token;
    }

    public String getToken_last_eight() {
        return token_last_eight;
    }

    public void setToken_last_eight(String token_last_eight) {
        this.token_last_eight = token_last_eight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote_url() {
        return note_url;
    }

    public void setNote_url(String note_url) {
        this.note_url = note_url;
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

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public List<?> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public static class AppBean {
        private String name;
        private String url;
        private String client_id;

        public AppBean() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }
    }

    public AuthenticationInfo() {
    }
}
