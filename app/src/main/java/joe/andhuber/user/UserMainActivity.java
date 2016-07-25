package joe.andhuber.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.function.FunctionActivity;
import joe.andhuber.user.adapter.UserMainPagerAdapter;
import joe.andhuber.user.fragment.RepositoryFragment;
import joe.andhuber.user.fragment.StarFragment;
import joe.andhuber.user.presenter.UserMainPresenter;
import joe.andhuber.user.presenter.UserMainPresenterImpl;
import joe.andhuber.user.view.UserMainView;
import joe.githubapi.model.user.UserInfo;

public class UserMainActivity extends BaseActivity implements UserMainView {

    private TabLayout tabLayout;
    private CollapsingToolbarLayout toolbarLayout;
    private CircleImageView headImg;
    private UserMainPresenter userPresenter;
    private ViewPager viewPager;
    private RepositoryFragment repositoryFragment;
    private StarFragment starFragment;
    private ProgressDialog dialog;
    private FloatingActionButton refreshBtn;
    private AppCompatTextView blogTxt, companyTxt, emailTxt, followerTxt, followingTxt;
    private int nowIndex = 0;
    private String userLogin;
    private boolean isHome = false;
    private ArrayList<Fragment> fragments;
    private UserMainPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLogin = getIntent().getStringExtra("user");
        isHome = getIntent().getBooleanExtra("isHome", false);
        userPresenter = new UserMainPresenterImpl(this);
        initViews();
        initListeners();
        userPresenter.getUserInfo(userLogin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isHome) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            MenuItem message = menu.findItem(R.id.menu_message);
            MenuItem setting = menu.findItem(R.id.menu_set);
            message.setVisible(true);
            setting.setVisible(true);
            return true;
        } else {
            return super.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_message:
                Intent intent = new Intent(this, FunctionActivity.class);
                intent.putExtra("type", FunctionActivity.EVENT_FRAGMENT);
                startActivity(intent);
                break;
            case R.id.menu_set:
                Intent intent2 = new Intent(this, FunctionActivity.class);
                intent2.putExtra("type", FunctionActivity.SETTING_FRAGMENT);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(null);
        }
        if (!isHome) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        headImg = (CircleImageView) findViewById(R.id.img_tool_head);
        blogTxt = (AppCompatTextView) findViewById(R.id.txt_main_user_blog);
        companyTxt = (AppCompatTextView) findViewById(R.id.txt_main_user_company);
        emailTxt = (AppCompatTextView) findViewById(R.id.txt_main_user_email);
        followerTxt = (AppCompatTextView) findViewById(R.id.txt_main_user_follower);
        followingTxt = (AppCompatTextView) findViewById(R.id.txt_main_user_following);
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_main);

        refreshBtn = (FloatingActionButton) findViewById(R.id.fab_main);
        if (refreshBtn != null) {
            refreshBtn.setOnClickListener(v -> userPresenter.refreshData(nowIndex));
        }
    }

    private void initListeners() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                nowIndex = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        repositoryFragment.moveToTop();
                        break;
                    case 1:
                        starFragment.moveToTop();
                        break;
                }
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
        dialog.setMessage(getString(R.string.getting_user_info));
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
    public void setBlog(String blogUrl) {
        if (TextUtils.isEmpty(blogUrl)) {
            blogUrl = "null";
        }
        blogTxt.setText(blogUrl);
    }

    @Override
    public void setCompany(String company) {
        if (TextUtils.isEmpty(company)) {
            company = "null";
        }
        companyTxt.setText(company);
    }

    @Override
    public void setEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            email = "null";
        }
        emailTxt.setText(email);
    }

    @Override
    public void setFollower(int num) {
        followerTxt.setText(String.valueOf(num));
    }

    @Override
    public void setFollowing(int num) {
        followingTxt.setText(String.valueOf(num));
    }

    @Override
    public void initFragments(UserInfo user) {
        fragments = new ArrayList<>();
        repositoryFragment = RepositoryFragment.newInstance(user);
        starFragment = StarFragment.newInstance(user);
        fragments.add(repositoryFragment);
        fragments.add(starFragment);
        adapter = new UserMainPagerAdapter(getSupportFragmentManager(), this, fragments);
        viewPager.setAdapter(adapter);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public boolean isHome() {
        return isHome;
    }
}