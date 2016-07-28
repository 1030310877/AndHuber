package joe.andhuber.user.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joe.rxbus.RxBus;
import com.joe.rxbus.ThreadMode;
import com.joe.rxbus.annotation.Subscriber;

import java.util.ArrayList;
import java.util.List;

import joe.andhuber.R;
import joe.andhuber.base.BaseFragment;
import joe.andhuber.repository.activity.RepoDetailActivity;
import joe.andhuber.user.adapter.StarsAdapter;
import joe.andhuber.user.presenter.StarPresenter;
import joe.andhuber.user.presenter.StarPresenterImpl;
import joe.andhuber.user.view.StarView;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.model.user.UserInfo;
import joe.view.recyclerview.LoadMoreRecyclerView;
import joe.view.recyclerview.SpaceItemDecoration;
import rx.functions.Action1;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class StarFragment extends BaseFragment implements StarView {
    private List<RepositoryInfo> data;
    private LoadMoreRecyclerView recyclerView;
    private StarsAdapter adapter;
    private StarPresenter presenter;
    private UserInfo user;
    private int page = 1;
    private ProgressDialog dialog;

    public static StarFragment newInstance(UserInfo user) {
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        StarFragment fragment = new StarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = new LoadMoreRecyclerView(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setPadding(16, 8, 16, 8);
        recyclerView.addItemDecoration(new SpaceItemDecoration(16));
        return recyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.user = (UserInfo) getArguments().getSerializable("user");
        presenter = new StarPresenterImpl(this);
        data = new ArrayList<>();
        adapter = new StarsAdapter(mContext, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnLoadingListener(() -> presenter.getUserStars(user.getLogin(), page + 1));
        adapter.setOnItemClickListener(position -> {
            if (position >= 0 && position < data.size()) {
                Intent intent = new Intent(mContext, RepoDetailActivity.class);
                intent.putExtra("repository", data.get(position));
                startActivity(intent);
            } else {
                adapter.notifyDataSetChanged();
            }
        });
        initStars();
    }

    @Override
    public void onResume() {
        super.onResume();
        RxBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        RxBus.getInstance().unRegister(this);
    }

    public void initStars() {
        presenter.getUserStars(user.getLogin(), 1);
    }

    @Subscriber(tag = "refreshStars", mode = ThreadMode.MAIN)
    public Action1 refresh = new Action1() {
        @Override
        public void call(Object o) {
            initStars();
        }
    };

    @Override
    public void addStars(List<RepositoryInfo> data) {
        int oldLen = this.data.size();
        int addLen = data.size();
        this.data.addAll(data);
        if (adapter != null) {
            adapter.setData(this.data);
            adapter.notifyItemRangeInserted(oldLen, addLen);
        }
    }

    @Override
    public void clearStars() {
        int oldLen = this.data.size();
        this.data.clear();
        adapter.setData(this.data);
        adapter.notifyItemRangeRemoved(0, oldLen);
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void loadFinish(String text) {
        if (recyclerView.isLoading) {
            recyclerView.loadFinished(text);
        }
    }

    @Override
    public void showWaitDialog() {
        dialog = new ProgressDialog(mContext);
        dialog.setMessage(getString(R.string.getting_star));
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void dismissWaitDialog() {
        if (dialog != null && getActivity() != null && !getActivity().isFinishing() && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void moveToTop() {
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }
}
