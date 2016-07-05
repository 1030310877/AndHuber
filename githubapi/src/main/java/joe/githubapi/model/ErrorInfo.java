package joe.githubapi.model;

import java.util.List;

/**
 * Description
 * Created by chenqiao on 2016/7/5.
 */
public class ErrorInfo {
    /**
     * message : Validation Failed
     * errors : [{"resource":"OauthAccess","code":"already_exists","field":"fingerprint"}]
     * documentation_url : https://developer.github.com/v3/oauth_authorizations/#create-a-new-authorization
     */

    private String message;
    private String documentation_url;
    /**
     * resource : OauthAccess
     * code : already_exists
     * field : fingerprint
     */

    private List<ErrorsBean> errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentation_url() {
        return documentation_url;
    }

    public void setDocumentation_url(String documentation_url) {
        this.documentation_url = documentation_url;
    }

    public List<ErrorsBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorsBean> errors) {
        this.errors = errors;
    }

    public static class ErrorsBean {
        private String resource;
        private String code;
        private String field;

        public ErrorsBean() {
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }

    public ErrorInfo() {
    }
}
