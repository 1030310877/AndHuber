package joe.githubapi.model.repositories;

import java.io.Serializable;

/**
 * Description
 * Created by chenqiao on 2016/8/11.
 */
public class CommentParam implements Serializable {
    private String body;
    private String path;
    private int position;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CommentParam() {
    }
}
