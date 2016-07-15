package joe.andhuber.repository;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.repository.presenter.RepoDetailPresenter;
import joe.andhuber.repository.presenter.RepoDetailPresenterImpl;
import joe.andhuber.repository.view.RepoDetailView;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class RepoDetailActivity extends BaseActivity implements RepoDetailView {

    private WebView markdownView;
    private ProgressDialog dialog;
    private RepoDetailPresenter presenter;
    private RepositoryInfo repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repodetail);
        presenter = new RepoDetailPresenterImpl(this);
        repository = (RepositoryInfo) getIntent().getSerializableExtra("repository");
        initViews();
        presenter.getReadMe(repository.getOwner().getLogin(), repository.getName());
    }

    private void initViews() {
        markdownView = (WebView) findViewById(R.id.wb_repodetail);
        if (markdownView != null) {
            markdownView.getSettings().setDefaultTextEncodingName("UTF-8");
        }
    }

    @Override
    public void loadReadMe(String content) {
        markdownView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }

    @Override
    public void showWaitDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.getting_information));
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void dismissWaitDialog() {
        if (dialog != null && !isFinishing() && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        markdownView.destroy();
    }
}