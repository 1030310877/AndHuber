package joe.andhuber.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import joe.andhuber.R;
import joe.andhuber.base.BaseActivity;
import joe.andhuber.login.model.User;
import joe.andhuber.login.presenter.LoginPresenter;
import joe.andhuber.login.presenter.LoginPresenterImpl;
import joe.andhuber.login.view.LoginView;
import joe.andhuber.main.MainActivity;
import joe.andhuber.utils.ToastUtil;
import joe.andhuber.utils.rx.RxView;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    private ProgressDialog dialog;
    private AppCompatEditText edt_userName, edt_passWord;
    private AppCompatButton btn_login;
    private AppCompatCheckBox ck_auto;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenterImpl(this);
        initViews();
        presenter.init();
        initListeners();
    }

    private void initViews() {
        edt_userName = (AppCompatEditText) findViewById(R.id.edt_login_username);
        edt_passWord = (AppCompatEditText) findViewById(R.id.edt_login_password);
        btn_login = (AppCompatButton) findViewById(R.id.btn_login_login);
        ck_auto = (AppCompatCheckBox) findViewById(R.id.ck_login_auto);
    }

    private void initListeners() {
        RxView.add("Login", RxView.click(btn_login).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(aVoid -> {
            presenter.login(new User(getUserName(), getPassWord()), null);
        }));
        ck_auto.setOnCheckedChangeListener((buttonView, isChecked) -> {
            presenter.rememberUser(isChecked);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void showWait() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.wait_to_login));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void dismissWait() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void showError(String error) {
        ToastUtil.show(this, error);
    }

    @Override
    public void showTwoFactorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText editText = new EditText(this);
        editText.setPadding(20, 0, 20, 0);
        editText.setHint(R.string.two_factor_hint);
        builder.setCancelable(true)
                .setTitle(R.string.authenticate)
                .setView(editText)
                .setPositiveButton(R.string.confirm, (dialog1, which) -> {
                    dialog1.dismiss();
                    presenter.login(new User(getUserName(), getPassWord()), editText.getEditableText().toString());
                });
        builder.create().show();
    }

    @Override
    public void startToMain() {
        ToastUtil.show(this, "登录成功");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public String getUserName() {
        return edt_userName.getEditableText().toString();
    }

    @Override
    public String getPassWord() {
        return edt_passWord.getEditableText().toString();
    }

    @Override
    public void setUserName(String username) {
        edt_userName.setText(username);
    }

    @Override
    public void setPassword(String password) {
        edt_passWord.setText(password);
    }

    @Override
    public void setRememberChecked(boolean isChecked) {
        ck_auto.setChecked(isChecked);
    }
}
