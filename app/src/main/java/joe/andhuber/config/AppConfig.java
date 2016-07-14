package joe.andhuber.config;

import android.content.Context;
import android.content.SharedPreferences;

import joe.andhuber.HuberApplication;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class AppConfig {

    private static AppConfig instance;
    private SharedPreferences preferences;

    private AppConfig() {

    }

    private AppConfig(String user) {
        preferences = HuberApplication.getInstance().getSharedPreferences(user, Context.MODE_PRIVATE);
    }

    public synchronized void init(String user) {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig(user);
                }
            }
        }
    }

    private SharedPreferences.Editor edit() {
        return preferences.edit();
    }

    public static AppConfig getInstance() {
        return instance;
    }

    private static final String ENABLE_SEARCH_SERVICE = "SEARCH_SERVICE";

    public void setEnableSearchService(boolean enable) {
        edit().putBoolean(ENABLE_SEARCH_SERVICE, enable).apply();
    }

    public boolean isEnableSearchService() {
        return preferences.getBoolean(ENABLE_SEARCH_SERVICE, true);
    }
}
