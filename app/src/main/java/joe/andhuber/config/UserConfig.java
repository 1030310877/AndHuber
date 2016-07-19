package joe.andhuber.config;

import joe.andhuber.HuberApplication;
import joe.andhuber.utils.SPUtil;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public class UserConfig {

    public static UserInfo nowUser;

    private UserConfig() {
    }

    private static UserConfig instance;

    public static UserConfig getInstance() {
        if (instance == null) {
            synchronized (UserConfig.class) {
                if (instance == null) {
                    instance = new UserConfig();
                }
            }
        }
        return instance;
    }

    private void put(String key, Object value) {
        SPUtil.put(HuberApplication.getInstance(), key, value);
    }

    private Object get(String key, Object defaultValue) {
        return SPUtil.get(HuberApplication.getInstance(), key, defaultValue);
    }

    private void remove(String key) {
        SPUtil.remove(HuberApplication.getInstance(), key);
    }

    private static final String REMEMBER_USER = "remember_user";

    public void setRememberUser(boolean tf) {
        put(REMEMBER_USER, tf);
    }

    public boolean isRememberUser() {
        return (boolean) get(REMEMBER_USER, false);
    }

    private static final String DEFAULT_USER = "default_user";
    private static final String DEFAULT_USER_PASSWORD = "default_user_password";
    private static final String DEFAULT_USER_INFO = "default_user_info";

    public void setDefaultUserInfo(String jsonInfo) {
        put(DEFAULT_USER_INFO, jsonInfo);
    }

    public String getDefaultUserInfo() {
        if (!getLoginAuto()) {
            return "";
        }
        return (String) get(DEFAULT_USER_INFO, "");
    }

    public void setDefaultUser(String username, String password) {
        put(DEFAULT_USER, username);
        put(DEFAULT_USER_PASSWORD, password);
    }

    public String getDefaultUser() {
        return (String) get(DEFAULT_USER, "");
    }

    public String getDefaultUserPassword() {
        return (String) get(DEFAULT_USER_PASSWORD, "");
    }

    public void clearDefaultUser() {
        remove(DEFAULT_USER);
        remove(DEFAULT_USER_PASSWORD);
    }

    private static final String TOKEN = "token";

    public void setToken(String token) {
        put(TOKEN, token);
    }

    public String getToken() {
        return (String) get(TOKEN, "");
    }

    public void removeToken() {
        remove(TOKEN);
    }

    private static final String LOGIN_AUTO = "login_auto";

    public void setLoginAuto(boolean tf) {
        put(LOGIN_AUTO, tf);
    }

    public boolean getLoginAuto() {
        return (boolean) get(LOGIN_AUTO, false);
    }
}