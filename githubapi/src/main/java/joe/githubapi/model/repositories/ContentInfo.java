package joe.githubapi.model.repositories;

import java.io.Serializable;

/**
 * Description
 * Created by chenqiao on 2016/7/15.
 */
public class ContentInfo implements Serializable {

    /**
     * name : .gitattributes
     * path : .gitattributes
     * sha : bdb0cabc87cf50106df6e15097dff816c8c3eb34
     * size : 378
     * url : https://api.github.com/repos/1030310877/AndHuber/contents/.gitattributes?ref=master
     * html_url : https://github.com/1030310877/AndHuber/blob/master/.gitattributes
     * git_url : https://api.github.com/repos/1030310877/AndHuber/git/blobs/bdb0cabc87cf50106df6e15097dff816c8c3eb34
     * download_url : https://raw.githubusercontent.com/1030310877/AndHuber/master/.gitattributes
     * type : file
     * _links : {"self":"https://api.github.com/repos/1030310877/AndHuber/contents/.gitattributes?ref=master","git":"https://api.github.com/repos/1030310877/AndHuber/git/blobs/bdb0cabc87cf50106df6e15097dff816c8c3eb34","html":"https://github.com/1030310877/AndHuber/blob/master/.gitattributes"}
     */

    private String name;
    private String path;
    private String sha;
    private long size;
    private String url;
    private String html_url;
    private String git_url;
    private String download_url;
    private String type;
    /**
     * self : https://api.github.com/repos/1030310877/AndHuber/contents/.gitattributes?ref=master
     * git : https://api.github.com/repos/1030310877/AndHuber/git/blobs/bdb0cabc87cf50106df6e15097dff816c8c3eb34
     * html : https://github.com/1030310877/AndHuber/blob/master/.gitattributes
     */

    private LinksBean _links;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

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

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LinksBean get_links() {
        return _links;
    }

    public void set_links(LinksBean _links) {
        this._links = _links;
    }

    public static class LinksBean implements Serializable {
        private String self;
        private String git;
        private String html;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getGit() {
            return git;
        }

        public void setGit(String git) {
            this.git = git;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }
    }
}
