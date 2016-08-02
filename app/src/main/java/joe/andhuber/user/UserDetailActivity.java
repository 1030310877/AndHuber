package joe.andhuber.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.user.presenter.UserDetailPresenter;
import joe.andhuber.user.presenter.UserDetailPresenterImpl;
import joe.andhuber.user.view.UserDetailView;
import joe.githubapi.model.user.UserParam;

/**
 * Description
 * Created by chenqiao on 2016/7/29.
 */
public class UserDetailActivity extends BaseActivity implements UserDetailView {

    private AppCompatTextView loginTxt;
    private AppCompatEditText nameTxt;
    private AppCompatTextView followerTxt;
    private AppCompatTextView starredTxt;
    private AppCompatTextView followingTxt;
    private AppCompatEditText emailTxt;
    private AppCompatEditText blogTxt;
    private AppCompatEditText companyTxt;
    private AppCompatEditText locationTxt;
    private AppCompatEditText bioTxt;
    private AppCompatImageView headImg, editImg;
    private AppCompatCheckBox hireableCb;
    private ProgressDialog dialog;
    private AppCompatButton saveBtn, cancelBtn;
    private UserDetailPresenter presenter;
    private View btnsView;
    private boolean isChanged = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        initViews();
        presenter = new UserDetailPresenterImpl(this);
        presenter.getUserInfo(getIntent());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                checkResult();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        checkResult();
        super.onBackPressed();
    }

    private void checkResult() {
        if (isChanged) {
            Intent intent = new Intent();
            intent.putExtra("user", presenter.getUserInfo());
            setResult(RESULT_OK, intent);
        }
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_userdetail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        headImg = (AppCompatImageView) findViewById(R.id.img_userdetail_head);
        editImg = (AppCompatImageView) findViewById(R.id.img_userdetail_edit);
        loginTxt = (AppCompatTextView) findViewById(R.id.txt_userdetail_login);
        followerTxt = (AppCompatTextView) findViewById(R.id.txt_userdetail_followers);
        starredTxt = (AppCompatTextView) findViewById(R.id.txt_userdetail_starred);
        followingTxt = (AppCompatTextView) findViewById(R.id.txt_userdetail_following);
        nameTxt = (AppCompatEditText) findViewById(R.id.txt_userdetail_name);
        emailTxt = (AppCompatEditText) findViewById(R.id.txt_userdetail_email);
        blogTxt = (AppCompatEditText) findViewById(R.id.txt_userdetail_blog);
        companyTxt = (AppCompatEditText) findViewById(R.id.txt_userdetail_company);
        bioTxt = (AppCompatEditText) findViewById(R.id.txt_userdetail_bio);
        locationTxt = (AppCompatEditText) findViewById(R.id.txt_userdetail_location);
        hireableCb = (AppCompatCheckBox) findViewById(R.id.cb_userdetail_hireable);
        btnsView = findViewById(R.id.layout_userdetail_btn);
        saveBtn = (AppCompatButton) findViewById(R.id.btn_userdetail_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveUser(getUserParam());
            }
        });
        cancelBtn = (AppCompatButton) findViewById(R.id.btn_userdetail_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditable(false);
                presenter.initInfo();
            }
        });
        editImg.setOnClickListener(v -> {
            setEditable(true);
            editImg.setVisibility(View.GONE);
        });
    }

    @Override
    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    @Override
    public void setEditable(boolean isEnable) {
        nameTxt.setEnabled(isEnable);
        emailTxt.setEnabled(isEnable);
        blogTxt.setEnabled(isEnable);
        companyTxt.setEnabled(isEnable);
        locationTxt.setEnabled(isEnable);
        bioTxt.setEnabled(isEnable);
        hireableCb.setEnabled(isEnable);
        showBtns(isEnable);
        showEditImg(!isEnable);
    }

    @Override
    public void showFollowers(int num) {
        followerTxt.setText(String.valueOf(num));
    }

    @Override
    public void showStarred(int num) {
        starredTxt.setText(String.valueOf(num));
    }

    @Override
    public void showFollowing(int num) {
        followingTxt.setText(String.valueOf(num));
    }

    @Override
    public void showHeadImg(String url) {
        Glide.with(this).load(url).asBitmap()
                .placeholder(R.drawable.head_placeholder)
                .into(new BitmapImageViewTarget(headImg) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        headImg.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    @Override
    public void showLoginName(String login) {
        loginTxt.setText(login);
    }

    @Override
    public void showName(String name) {
        if (TextUtils.isEmpty(name)) {
            name = "";
        }
        nameTxt.setText(name);
    }

    @Override
    public void showEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            email = "";
        }
        emailTxt.setText(email);
    }

    @Override
    public void showBlog(String blog) {
        if (TextUtils.isEmpty(blog)) {
            blog = "";
        }
        blogTxt.setText(blog);
    }

    @Override
    public void showBio(String bio) {
        if (TextUtils.isEmpty(bio)) {
            bio = "";
        }
        bioTxt.setText(bio);
    }

    @Override
    public void showCompany(String company) {
        if (TextUtils.isEmpty(company)) {
            company = "";
        }
        companyTxt.setText(company);
    }

    @Override
    public void showLocation(String location) {
        if (TextUtils.isEmpty(location)) {
            location = "";
        }
        locationTxt.setText(location);
    }

    @Override
    public void showHireable(boolean hireable) {
        hireableCb.setChecked(hireable);
    }

    @Override
    public void showBtns(boolean isShown) {
        if (isShown) {
            btnsView.setVisibility(View.VISIBLE);
        } else {
            btnsView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEditImg(boolean isShown) {
        if (isShown) {
            editImg.setVisibility(View.VISIBLE);
        } else {
            editImg.setVisibility(View.GONE);
        }
    }

    @Override
    public void showWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.saving_user_info));
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

    public UserParam getUserParam() {
        UserParam userParam = new UserParam();
        userParam.setName(nameTxt.getEditableText().toString());
        userParam.setEmail(emailTxt.getEditableText().toString());
        userParam.setBlog(blogTxt.getEditableText().toString());
        userParam.setCompany(companyTxt.getEditableText().toString());
        userParam.setLocation(locationTxt.getEditableText().toString());
        userParam.setBio(bioTxt.getEditableText().toString());
        userParam.setHireable(hireableCb.isChecked());
        return userParam;
    }
}
