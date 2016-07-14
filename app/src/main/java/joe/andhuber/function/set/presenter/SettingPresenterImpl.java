package joe.andhuber.function.set.presenter;

import android.content.Intent;

import joe.andhuber.HuberApplication;
import joe.andhuber.base.ActivityStack;
import joe.andhuber.function.set.view.SettingView;
import joe.andhuber.model.user.UserModel;
import joe.andhuber.search.service.SearchService;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class SettingPresenterImpl implements SettingPresenter {
    private SettingView settingView;
    private UserModel userModel;

    public SettingPresenterImpl(SettingView settingView) {
        this.settingView = settingView;
        userModel = new UserModel();
    }

    @Override
    public void logout() {
        userModel.logout();
        ActivityStack.clear();
        settingView.jumpToLoginActivity();
    }

    @Override
    public void enableSearchService(boolean enable) {
        Intent intent = new Intent(HuberApplication.getInstance(), SearchService.class);
        if (enable) {
            HuberApplication.getInstance().startService(intent);
        } else {
            HuberApplication.getInstance().stopService(intent);
        }
    }
}
