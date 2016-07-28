package joe.andhuber.repository.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.repository.presenter.FileContentPresenter;
import joe.andhuber.repository.presenter.FileContentPresenterImpl;
import joe.andhuber.repository.view.FileContentView;
import joe.andhuber.utils.FileTypeUtil;
import joe.view.webviewcode.CodeView;
import joe.view.webviewcode.Settings;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public class FileContentActivity extends BaseActivity implements FileContentView {

    public static final int TEXT = 0;
    public static final int CODE = 1;

    private FrameLayout contentView;
    private ProgressDialog dialog;
    private String fileName;
    private String download_url;
    private String content;
    private FileContentPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_content);
        fileName = getIntent().getStringExtra("fileName");
        download_url = getIntent().getStringExtra("url");
        content = getIntent().getStringExtra("content");
        initViews();
        presenter = new FileContentPresenterImpl(this);
        if (TextUtils.isEmpty(content)) {
            getContent();
        } else {
            showText(content);
        }
    }

    private void getContent() {
        if (FileTypeUtil.isPicture(fileName)) {
            showPicture(download_url);
        } else if (FileTypeUtil.isCode(fileName)) {
            presenter.getFileRaw(download_url, CODE);
        } else if (FileTypeUtil.isVoice(fileName)) {

        } else {
            presenter.getFileRaw(download_url, TEXT);
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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle(fileName);
        contentView = (FrameLayout) findViewById(R.id.layout_file_content);
    }

    @Override
    public void showWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.getting_file_content));
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

    @Override
    public void showPicture(String url) {
        contentView.removeAllViews();
        ImageView imageView = new ImageView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.addView(imageView, params);
        Glide.with(this).load(download_url).placeholder(R.drawable.pic_placeholder).crossFade().into(imageView);
    }

    @Override
    public void showCode(String code) {
        contentView.removeAllViews();
        WebView webView = new WebView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.addView(webView, params);
        CodeView.with(this).withCode(code).setStyle(Settings.WithStyle.DARKULA).into(webView);
    }

    @Override
    public void showText(String content) {
        contentView.removeAllViews();
        WebView webView = new WebView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.addView(webView, params);
        webView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }
}
