package joe.andhuber.repository.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.repository.fragment.FileListFragment;
import joe.andhuber.repository.fragment.ReadMeFragment;
import joe.andhuber.repository.presenter.RepoDetailPresenter;
import joe.andhuber.repository.presenter.RepoDetailPresenterImpl;
import joe.andhuber.repository.view.RepoDetailView;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class RepoDetailActivity extends BaseActivity implements RepoDetailView {

    private ProgressDialog dialog;
    private RepoDetailPresenter presenter;
    private RepositoryInfo repository;
    private TabLayout tablayout;
    private CollapsingToolbarLayout toolbarLayout;
    private ViewPager viewPager;
    private AppCompatTextView starTxt, watchTxt, forkTxt;
    private AppCompatImageView starImg, watchImg;
    private boolean isStarred = false;
    private boolean isWatched = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repodetail);
        presenter = new RepoDetailPresenterImpl(this);
        repository = (RepositoryInfo) getIntent().getSerializableExtra("repository");
        initViews();
        if (repository == null) {
            repository = new RepositoryInfo();
        }
        presenter.checkIsStarred(repository.getOwner().getLogin(), repository.getName());
        presenter.checkIsWatched(repository.getOwner().getLogin(), repository.getName());
    }

    private void initViews() {
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_repodetail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_repodetail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbarLayout.setTitle(repository.getName());
        tablayout = (TabLayout) findViewById(R.id.tablayout_repodetail);
        viewPager = (ViewPager) findViewById(R.id.vp_repodetail);
        ArrayList<Fragment> fragments = new ArrayList<>();
        ReadMeFragment readMeFragment = ReadMeFragment.newInstance(repository);
        FileListFragment fileListFragment = FileListFragment.newInstance(repository);
        fragments.add(readMeFragment);
        fragments.add(fileListFragment);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private String[] titles = new String[]{"ReadMe", "Code"};

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

        starTxt = (AppCompatTextView) findViewById(R.id.txt_repodetail_star);
        starImg = (AppCompatImageView) findViewById(R.id.img_repodetail_star);
        watchTxt = (AppCompatTextView) findViewById(R.id.txt_repodetail_watch);
        watchImg = (AppCompatImageView) findViewById(R.id.img_repodetail_watch);
        forkTxt = (AppCompatTextView) findViewById(R.id.txt_repodetail_fork);

        showStars(repository.getStargazers_count());
        showWatchers(repository.getWatchers_count());
        showForks(repository.getForks_count());

        forkTxt.setOnClickListener(v -> presenter.forkRepository(repository.getOwner().getLogin(), repository.getName(), null));
        View watchView = findViewById(R.id.layout_repodetail_watch);
        if (watchView != null) {
            watchView.setOnClickListener(v -> {
                if (isWatched) {
                    presenter.unWatchRepository(repository.getOwner().getLogin(), repository.getName());
                    setIsWatched(false);
                } else {
                    presenter.watchRepository(repository.getOwner().getLogin(), repository.getName());
                    setIsWatched(true);
                }
            });
        }
        View starView = findViewById(R.id.layout_repodetail_star);
        if (starView != null) {
            starView.setOnClickListener(v -> {
                if (isStarred) {
                    presenter.unStarRepository(repository.getOwner().getLogin(), repository.getName());
                    setIsStarred(false);
                } else {
                    presenter.starRepository(repository.getOwner().getLogin(), repository.getName());
                    setIsStarred(true);
                }
            });
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
    public void showStars(int count) {
        starTxt.setText(String.valueOf(count));
    }

    @Override
    public void setIsStarred(boolean isStarred) {
        this.isStarred = isStarred;
        if (isStarred) {
            starImg.setImageResource(R.drawable.ic_stars_black_36dp);
        } else {
            starImg.setImageResource(R.drawable.ic_star_white_36dp);
        }
    }

    @Override
    public void showWatchers(int count) {
        watchTxt.setText(String.valueOf(count));
    }

    @Override
    public void setIsWatched(boolean isWatched) {
        this.isWatched = isWatched;
        if (isWatched) {
            watchImg.setImageResource(R.drawable.ic_remove_red_eye_black_36dp);
        } else {
            watchImg.setImageResource(R.drawable.ic_remove_red_eye_white_36dp);
        }
    }

    @Override
    public void showForks(int count) {
        forkTxt.setText(String.valueOf(count));
    }
}