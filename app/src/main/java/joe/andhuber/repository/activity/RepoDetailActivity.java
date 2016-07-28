package joe.andhuber.repository.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

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
    }

    private void initViews() {
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_repodetail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_repodetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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

}