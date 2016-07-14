package joe.andhuber.user.fragment;

import android.app.ProgressDialog;
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
import joe.andhuber.user.adapter.RepositoryAdapter;
import joe.andhuber.user.presenter.RepositoryPresenter;
import joe.andhuber.user.presenter.RepositoryPresenterImpl;
import joe.andhuber.user.view.RepositoryView;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.githubapi.model.user.UserInfo;
import joe.view.recyclerview.LoadMoreRecyclerView;
import joe.view.recyclerview.SpaceItemDecoration;
import rx.functions.Action1;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class RepositoryFragment extends BaseFragment implements RepositoryView {

    private RepositoryAdapter adapter;
    private List<RepositoryInfo> data;
    private LoadMoreRecyclerView recyclerView;
    private RepositoryPresenter presenter;
    private UserInfo user;
    private int page = 1;
    private ProgressDialog dialog;

    public static RepositoryFragment newInstance(UserInfo user) {
        Bundle args = new Bundle();
        args.putSerializable("user", user);
        RepositoryFragment fragment = new RepositoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = new LoadMoreRecyclerView(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setPadding(20, 10, 20, 10);
        recyclerView.addItemDecoration(new SpaceItemDecoration(25));
        return recyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.user = (UserInfo) getArguments().getSerializable("user");
        presenter = new RepositoryPresenterImpl(this);
        data = new ArrayList<>();
        adapter = new RepositoryAdapter(mContext, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnLoadingListener(new LoadMoreRecyclerView.onLoadingMoreListener() {
            @Override
            public void onLoading() {
                presenter.getUserRepositories(user.getLogin(), page + 1);
            }
        });
        initRepositories();
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

    @Subscriber(tag = "refreshRepository", mode = ThreadMode.NEW_THREAD)
    public Action1 refresh = new Action1() {
        @Override
        public void call(Object o) {
            initRepositories();
        }
    };

    public void initRepositories() {
        clearRepositories();
        presenter.getUserRepositories(user.getLogin(), 1);
    }

    @Override
    public void addRepositories(List<RepositoryInfo> data) {
        int oldLen = this.data.size();
        int addLen = data.size();
        this.data.addAll(data);
        if (adapter != null) {
            adapter.setData(this.data);
            adapter.notifyItemRangeInserted(oldLen, addLen);
        }
    }

    @Override
    public void clearRepositories() {
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
        dialog.setMessage(getString(R.string.getting_repository));
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
