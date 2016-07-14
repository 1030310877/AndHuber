package joe.andhuber.function;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.function.event.fragment.EventsFragment;
import joe.andhuber.function.set.fragment.SettingFragment;

/**
 * Description
 * Created by chenqiao on 2016/7/13.
 */
public class FunctionActivity extends BaseActivity {

    public static final int EVENT_FRAGMENT = 0x0a;
    public static final int SETTING_FRAGMENT = 0x0b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        initViews();
        int type = getIntent().getIntExtra("type", -1);
        switch (type) {
            case EVENT_FRAGMENT:
                replaceFragment(new EventsFragment(), "EventsFragment", false);
                break;
            case SETTING_FRAGMENT:
                setTheme(R.style.PreferenceTheme);
                replaceFragment(new SettingFragment(), "SettingFragment", false);
                break;
        }
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void replaceFragment(Fragment fragment, String tag, boolean addToBack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (addToBack) {
            transaction.replace(R.id.layout_fun_root, fragment, tag).addToBackStack(tag);
        } else {
            transaction.replace(R.id.layout_fun_root, fragment, tag);
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
