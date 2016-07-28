package joe.andhuber.repository.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.repository.adapter.RepoFileAdapter;
import joe.andhuber.repository.presenter.RepoDetailPresenter;
import joe.andhuber.repository.presenter.RepoDetailPresenterImpl;
import joe.andhuber.repository.view.RepoDetailView;
import joe.githubapi.model.repositories.ContentInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.view.recyclerview.DividerItemDecoration;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class RepoDetailActivity extends BaseActivity implements RepoDetailView {

    private ProgressDialog dialog;
    private RepoDetailPresenter presenter;
    private RepositoryInfo repository;
    private RepoFileAdapter adapter;
    private ArrayList<ContentInfo> data;
    private AppCompatTextView startTxt, forkTxt;
    private AppCompatImageView startImg;
    private LinearLayout startLayout;
    private boolean isStarred = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repodetail);
        presenter = new RepoDetailPresenterImpl(this);
        repository = (RepositoryInfo) getIntent().getSerializableExtra("repository");
        initViews();
        if (repository == null) {
            return;
        }
        presenter.getFilesByPath(repository.getOwner().getLogin(), repository.getName(), "");
        presenter.checkIsStarred(repository.getOwner().getLogin(), repository.getName());
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle(repository.getFull_name());
        startTxt = (AppCompatTextView) findViewById(R.id.txt_repodetail_star);
        startImg = (AppCompatImageView) findViewById(R.id.img_repodetail_star);
        startLayout = (LinearLayout) findViewById(R.id.layout_repodetail_star);
        startLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarred) {
                    presenter.unStarRepository(repository.getOwner().getLogin(), repository.getName());
                } else {
                    presenter.starRepository(repository.getOwner().getLogin(), repository.getName());
                }
            }
        });
        forkTxt = (AppCompatTextView) findViewById(R.id.txt_repodetail_fork);
        RecyclerView filesView = (RecyclerView) findViewById(R.id.recycler_repodetail);
        if (filesView != null) {
            filesView.setLayoutManager(new LinearLayoutManager(this));
            filesView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
            data = new ArrayList<>();
            adapter = new RepoFileAdapter(data);
            filesView.setAdapter(adapter);
            adapter.setOnItemClickListener(position -> {
                if (position - 1 < data.size()) {
                    ContentInfo info = data.get(position - 1);
                    String path = info.getPath();
                    if (info.getType().equals("dir")) {
                        presenter.getFilesByPath(repository.getOwner().getLogin(), repository.getName(), path);
                    } else if (info.getType().equals("file")) {
                        startToContentView(info.getName(), info.getDownload_url(), "");
                    }
                }
            });
            adapter.setOnPathClick(path -> presenter.getFilesByPath(repository.getOwner().getLogin(), repository.getName(), path));
        }
        showStar(repository.getWatchers_count());
        showFork(repository.getForks_count());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_readme:
                presenter.getReadMe(repository.getOwner().getLogin(), repository.getName());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void showPath(List<String> paths) {
        adapter.setNowPath(paths);
        adapter.notifyItemChanged(0);
    }

    @Override
    public void showFiles(List<ContentInfo> files) {
        data.clear();
        data.addAll(files);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showStar(int num) {
        startTxt.setText(String.valueOf(num));
    }

    @Override
    public void setStarred(boolean isStarred) {
        if (isStarred) {
            startImg.setImageResource(R.drawable.ic_stars_black_18dp);
        } else {
            startImg.setImageResource(R.drawable.ic_star_border_black_18dp);
        }
        this.isStarred = isStarred;
    }

    @Override
    public void showFork(int num) {
        forkTxt.setText(String.valueOf(num));
    }

    @Override
    public void startToContentView(String fileName, String url, String content) {
        Intent intent = new Intent(this, FileContentActivity.class);
        intent.putExtra("fileName", fileName);
        intent.putExtra("url", url);
        intent.putExtra("content", content);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}