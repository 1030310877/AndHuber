package joe.githubapi.model.search;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 * Created by chenqiao on 2016/7/26.
 */
public class TextMatchesBean implements Serializable {
    private String object_url;
    private String object_type;
    private String property;
    private String fragment;
    /**
     * text : joewalnes
     * indices : [0,9]
     */

    private List<MatchesBean> matches;

    public String getObject_url() {
        return object_url;
    }

    public void setObject_url(String object_url) {
        this.object_url = object_url;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public List<MatchesBean> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchesBean> matches) {
        this.matches = matches;
    }

    public static class MatchesBean implements Serializable {
        private String text;
        private List<Integer> indices;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Integer> getIndices() {
            return indices;
        }

        public void setIndices(List<Integer> indices) {
            this.indices = indices;
        }
    }
}
