package joe.githubapi.model.repositories;

import java.io.Serializable;
import java.util.List;

import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/8/5.
 */
public class CommitInfo implements Serializable {

    /**
     * url : https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
     * html_url : https://github.com/octocat/Hello-World/commit/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * comments_url : https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e/comments
     * commit : {"url":"https://api.github.com/repos/octocat/Hello-World/git/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e","author":{"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"},"committer":{"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"},"message":"Fix all the bugs","tree":{"url":"https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"},"comment_count":0,"verification":{"verified":true,"reason":"valid","signature":"-----BEGIN PGP MESSAGE-----\n...\n-----END PGP MESSAGE-----","payload":"tree 6dcb09b5b57875f334f61aebed695e2e4193db5e\n..."}}
     * author : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * committer : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * parents : [{"url":"https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"}]
     */

    private String url;
    private String sha;
    private String html_url;
    private String comments_url;
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/git/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * author : {"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"}
     * committer : {"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"}
     * message : Fix all the bugs
     * tree : {"url":"https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"}
     * comment_count : 0
     * verification : {"verified":true,"reason":"valid","signature":"-----BEGIN PGP MESSAGE-----\n...\n-----END PGP MESSAGE-----","payload":"tree 6dcb09b5b57875f334f61aebed695e2e4193db5e\n..."}
     */

    private CommitBean commit;
    /**
     * login : octocat
     * id : 1
     * avatar_url : https://github.com/images/error/octocat_happy.gif
     * gravatar_id :
     * url : https://api.github.com/users/octocat
     * html_url : https://github.com/octocat
     * followers_url : https://api.github.com/users/octocat/followers
     * following_url : https://api.github.com/users/octocat/following{/other_user}
     * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
     * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/octocat/subscriptions
     * organizations_url : https://api.github.com/users/octocat/orgs
     * repos_url : https://api.github.com/users/octocat/repos
     * events_url : https://api.github.com/users/octocat/events{/privacy}
     * received_events_url : https://api.github.com/users/octocat/received_events
     * type : User
     * site_admin : false
     */

    private UserInfo author;
    /**
     * login : octocat
     * id : 1
     * avatar_url : https://github.com/images/error/octocat_happy.gif
     * gravatar_id :
     * url : https://api.github.com/users/octocat
     * html_url : https://github.com/octocat
     * followers_url : https://api.github.com/users/octocat/followers
     * following_url : https://api.github.com/users/octocat/following{/other_user}
     * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
     * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/octocat/subscriptions
     * organizations_url : https://api.github.com/users/octocat/orgs
     * repos_url : https://api.github.com/users/octocat/repos
     * events_url : https://api.github.com/users/octocat/events{/privacy}
     * received_events_url : https://api.github.com/users/octocat/received_events
     * type : User
     * site_admin : false
     */

    private UserInfo committer;
    /**
     * url : https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
     */

    private List<ParentsBean> parents;
    /**
     * additions : 104
     * deletions : 4
     * total : 108
     */

    private StatsBean stats;
    /**
     * filename : file1.txt
     * additions : 10
     * deletions : 2
     * changes : 12
     * status : modified
     * raw_url : https://github.com/octocat/Hello-World/raw/7ca483543807a51b6079e54ac4cc392bc29ae284/file1.txt
     * blob_url : https://github.com/octocat/Hello-World/blob/7ca483543807a51b6079e54ac4cc392bc29ae284/file1.txt
     * patch : @@ -29,7 +29,7 @@
     * .....
     */

    private List<FilesBean> files;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public CommitBean getCommit() {
        return commit;
    }

    public void setCommit(CommitBean commit) {
        this.commit = commit;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public UserInfo getCommitter() {
        return committer;
    }

    public void setCommitter(UserInfo committer) {
        this.committer = committer;
    }

    public List<ParentsBean> getParents() {
        return parents;
    }

    public void setParents(List<ParentsBean> parents) {
        this.parents = parents;
    }

    public StatsBean getStats() {
        return stats;
    }

    public void setStats(StatsBean stats) {
        this.stats = stats;
    }

    public List<FilesBean> getFiles() {
        return files;
    }

    public void setFiles(List<FilesBean> files) {
        this.files = files;
    }

    public static class CommitBean implements Serializable {
        private String url;
        /**
         * name : Monalisa Octocat
         * email : support@github.com
         * date : 2011-04-14T16:00:49Z
         */

        private User author;
        /**
         * name : Monalisa Octocat
         * email : support@github.com
         * date : 2011-04-14T16:00:49Z
         */

        private User committer;
        private String message;
        /**
         * url : https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e
         * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
         */

        private TreeBean tree;
        private int comment_count;
        /**
         * verified : true
         * reason : valid
         * signature : -----BEGIN PGP MESSAGE-----
         * ...
         * -----END PGP MESSAGE-----
         * payload : tree 6dcb09b5b57875f334f61aebed695e2e4193db5e
         * ...
         */

        private VerificationBean verification;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public User getAuthor() {
            return author;
        }

        public void setAuthor(User author) {
            this.author = author;
        }

        public User getCommitter() {
            return committer;
        }

        public void setCommitter(User committer) {
            this.committer = committer;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public TreeBean getTree() {
            return tree;
        }

        public void setTree(TreeBean tree) {
            this.tree = tree;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public VerificationBean getVerification() {
            return verification;
        }

        public void setVerification(VerificationBean verification) {
            this.verification = verification;
        }

        public static class User implements Serializable {
            private String name;
            private String email;
            private String date;

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

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }

        public static class TreeBean implements Serializable {
            private String url;
            private String sha;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }
        }

        public static class VerificationBean implements Serializable {
            private boolean verified;
            private String reason;
            private String signature;
            private String payload;

            public boolean isVerified() {
                return verified;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getPayload() {
                return payload;
            }

            public void setPayload(String payload) {
                this.payload = payload;
            }
        }
    }

    public static class ParentsBean implements Serializable {
        private String url;
        private String sha;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }
    }

    public static class StatsBean implements Serializable {
        private int additions;
        private int deletions;
        private int total;

        public int getAdditions() {
            return additions;
        }

        public void setAdditions(int additions) {
            this.additions = additions;
        }

        public int getDeletions() {
            return deletions;
        }

        public void setDeletions(int deletions) {
            this.deletions = deletions;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class FilesBean implements Serializable {
        private String filename;
        private int additions;
        private int deletions;
        private int changes;
        private String status;
        private String raw_url;
        private String blob_url;
        private String patch;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public int getAdditions() {
            return additions;
        }

        public void setAdditions(int additions) {
            this.additions = additions;
        }

        public int getDeletions() {
            return deletions;
        }

        public void setDeletions(int deletions) {
            this.deletions = deletions;
        }

        public int getChanges() {
            return changes;
        }

        public void setChanges(int changes) {
            this.changes = changes;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRaw_url() {
            return raw_url;
        }

        public void setRaw_url(String raw_url) {
            this.raw_url = raw_url;
        }

        public String getBlob_url() {
            return blob_url;
        }

        public void setBlob_url(String blob_url) {
            this.blob_url = blob_url;
        }

        public String getPatch() {
            return patch;
        }

        public void setPatch(String patch) {
            this.patch = patch;
        }
    }
}
