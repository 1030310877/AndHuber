package joe.andhuber.function.set.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import joe.andhuber.R;
import joe.andhuber.function.set.presenter.SettingPresenter;
import joe.andhuber.function.set.presenter.SettingPresenterImpl;
import joe.andhuber.function.set.view.SettingView;
import joe.andhuber.login.LoginActivity;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class SettingFragment extends PreferenceFragmentCompat implements SettingView, SharedPreferences.OnSharedPreferenceChangeListener {

    private SettingPresenter presenter;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.setting);
        presenter = new SettingPresenterImpl(this);
        Preference logout = findPreference("logout");
        logout.setOnPreferenceClickListener(preference -> {
            presenter.logout();
            return true;
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.setting);
    }

    @Override
    public void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        PreferenceManager.getDefaultSharedPreferences(getActivity()).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void jumpToLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.key_search_service))) {
            presenter.enableSearchService(sharedPreferences.getBoolean(key, true));
        }
    }
}