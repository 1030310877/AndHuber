package joe.andhuber.repository.activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;

import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.repository.adapter.CommitDetailAdapter;
import joe.andhuber.repository.fragment.CommentFragment;
import joe.andhuber.repository.fragment.DiffFragment;
import joe.andhuber.repository.view.CommitDetailView;
import joe.githubapi.model.repositories.CommitInfo;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/8/22.
 */
public class CommitDetailActivity extends BaseActivity implements CommitDetailView {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CommitInfo commitInfo;
    private RepositoryInfo repositoryInfo;
    private AppCompatImageView headImg;
    private AppCompatTextView msgTv, nameTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_detail);
        commitInfo = (CommitInfo) getIntent().getSerializableExtra("commitInfo");
        repositoryInfo = (RepositoryInfo) getIntent().getSerializableExtra("repositoryInfo");
        initViews();
        initFragments();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_commit_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewPager = (ViewPager) findViewById(R.id.vp_commit_detail);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_commit_detail);

        headImg = (AppCompatImageView) findViewById(R.id.img_commit_head);
        msgTv = (AppCompatTextView) findViewById(R.id.txt_commit_msg);
        nameTv = (AppCompatTextView) findViewById(R.id.txt_commit_name);
        if (commitInfo.getCommitter() != null) {
            setHead(commitInfo.getCommitter().getAvatar_url());
        }
        setCommitMsg(commitInfo.getCommit().getMessage());
        if (!TextUtils.isEmpty(commitInfo.getCommitter().getName())) {
            setName(commitInfo.getCommitter().getName());
        } else {
            setName(commitInfo.getCommitter().getLogin());
        }
    }

    private void initFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        DiffFragment diffFragment = DiffFragment.newInstance(commitInfo);
        CommentFragment commentFragment = CommentFragment.newInstance(repositoryInfo, commitInfo);
        fragments.add(diffFragment);
        fragments.add(commentFragment);
        CommitDetailAdapter adapter = new CommitDetailAdapter(getSupportFragmentManager(), this, fragments);
        viewPager.setAdapter(adapter);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void showWaitDialog() {

    }

    @Override
    public void dismissWaitDialog() {

    }

    @Override
    public void setHead(String imgUrl) {
        Glide.with(this).load(imgUrl).asBitmap()
                .placeholder(R.drawable.head_placeholder)
                .into(new BitmapImageViewTarget(headImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(headImg.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        headImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    @Override
    public void setName(String name) {
        nameTv.setText(name);
    }

    @Override
    public void setCommitMsg(String msg) {
        msgTv.setText(msg);
    }
}
