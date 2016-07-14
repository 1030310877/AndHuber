package joe.andhuber;

import android.content.Intent;

import joe.andhuber.application.BaseApplication;
import joe.andhuber.search.service.SearchService;
import joe.githubapi.core.GitHubApi;

/**
 * Description
 * Created by chenqiao on 2016/7/5.
 */
public class HuberApplication extends BaseApplication {
    private static HuberApplication instance;

    @Override
    protected void onBaseCreate() {
        instance = this;
        setCrashHandlerEnable(false);
        GitHubApi.init(this, true);
        Intent intent = new Intent(this, SearchService.class);
        startService(intent);
    }

    public static HuberApplication getInstance() {
        return instance;
    }
}
