package joe.andhuber.main;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.main.adapter.MainPagerAdapter;
import joe.andhuber.main.fragment.RepositoryFragment;
import joe.andhuber.main.fragment.StarFragment;
import joe.andhuber.main.presenter.MainPresenter;
import joe.andhuber.main.presenter.MainPresenterImpl;
import joe.andhuber.main.view.MainView;

public class MainActivity extends BaseActivity implements MainView {

    private TabLayout tabLayout;
    private CollapsingToolbarLayout toolbarLayout;
    private CircleImageView headImg;
    private MainPresenter userPresenter;
    private ViewPager viewPager;
    private RepositoryFragment repositoryFragment;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPresenter = new MainPresenterImpl(this);
        initViews();
        initListeners();
        userPresenter.initInformation();
    }

    private void initViews() {
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        headImg = (CircleImageView) findViewById(R.id.img_tool_head);
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        ArrayList<Fragment> fragments = new ArrayList<>();
        repositoryFragment = new RepositoryFragment();
        userPresenter.setRepositoryView(repositoryFragment);
        fragments.add(repositoryFragment);
        fragments.add(new StarFragment());
        fragments.add(new RepositoryFragment());
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), this, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_main);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void initListeners() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void setHeadImg(Bitmap bitmap) {
        headImg.setImageBitmap(bitmap);
    }

    @Override
    public void setHeadImg(@DrawableRes int resId) {
        headImg.setImageResource(resId);
    }

    @Override
    public void setHeadImg(String url) {
        Glide.with(this).load(url).into(headImg);
    }

    @Override
    public void setTitle(String title) {
        toolbarLayout.setTitle(title);
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