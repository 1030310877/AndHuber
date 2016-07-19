package joe.andhuber.repository;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.repository.view.FileContentView;
import joe.view.webviewcode.CodeView;
import joe.view.webviewcode.Settings;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public class FileContentActivity extends BaseActivity implements FileContentView {

    private WebView contentView;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_content);
        initViews();
        String content = getIntent().getStringExtra("content");
        int type = getIntent().getIntExtra("type", -1);
        if (content != null && type != -1) {
            switch (type) {
                case 0:
                    showReadMe(content);
                    break;
                case 1:
                    showCode(content);
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("fileName"));
        contentView = (WebView) findViewById(R.id.wb_file_content);
        contentView.getSettings().setUseWideViewPort(true);
    }

    @Override
    public void showReadMe(String content) {
        contentView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }

    @Override
    public void showCode(String code) {
        CodeView.with(this).withCode(code).setStyle(Settings.WithStyle.DARKULA).into(contentView);
    }

    @Override
    public void showWaitDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.getting_information));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void dismissWaitDialog() {
        if (dialog != null && !isFinishing()) {
            dialog.dismiss();
        }
    }
}
