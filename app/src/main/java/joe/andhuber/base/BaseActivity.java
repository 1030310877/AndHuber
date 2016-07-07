package joe.andhuber.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.remove(this);
    }
}
