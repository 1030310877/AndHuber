package joe.githubapi.model.authentication;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 * Created by chenqiao on 2016/7/4.
 */
public class AuthorizationParam implements Serializable {
    private String client_id;
    private String client_secret;
    private String note;
    private String fingerprint;
    private String note_url;
    private List<String> scopes;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getNote_url() {
        return note_url;
    }

    public void setNote_url(String note_url) {
        this.note_url = note_url;
    }

    public AuthorizationParam() {
    }

    public AuthorizationParam(String client_id, String fingerprint, String note_url, String note, String client_secret) {
        this.client_id = client_id;
        this.fingerprint = fingerprint;
        this.note_url = note_url;
        this.note = note;
        this.client_secret = client_secret;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
}
