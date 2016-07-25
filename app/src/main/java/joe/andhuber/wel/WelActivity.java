package joe.andhuber.wel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import joe.andhuber.R;
import joe.andhuber.config.UserConfig;
import joe.andhuber.login.LoginActivity;
import joe.andhuber.user.UserMainActivity;
import rx.Observable;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public class WelActivity extends AppCompatActivity implements WelView {

    private WelPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel);
        if (UserConfig.getInstance().getLoginAuto()) {
            presenter = new WelPresenterImpl(this);
            presenter.checkAuthorization();
        } else {
            Observable.timer(2, TimeUnit.SECONDS).subscribe(aLong -> {
                startToLogin();
            });
        }
    }

    @Override
    public void startToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startToMain(String user) {
        Intent intent = new Intent(this, UserMainActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("isHome", true);
        startActivity(intent);
        finish();
    }
}
