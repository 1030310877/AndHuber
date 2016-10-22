package joe.andhuber.repository.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joe.rxbus.RxBus;
import com.joe.rxbus.annotation.Subscriber;

import java.util.ArrayList;
import java.util.List;

import joe.andhuber.R;
import joe.andhuber.base.BaseFragment;
import joe.andhuber.model.repository.CommitParams;
import joe.andhuber.repository.activity.CommitDetailActivity;
import joe.andhuber.repository.adapter.CommitAdapter;
import joe.andhuber.repository.presenter.CommitsPresenter;
import joe.andhuber.repository.presenter.CommitsPresenterImpl;
import joe.andhuber.repository.view.CommitsView;
import joe.githubapi.model.repositories.CommitInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.view.recyclerview.LoadMoreRecyclerView;
import joe.view.recyclerview.OnItemClickListener;
import joe.view.recyclerview.SpaceItemDecoration;
import rx.functions.Action1;

/**
 * Description
 * Created by chenqiao on 2016/7/29.
 */
public class CommitsFragment extends BaseFragment implements CommitsView {
    private List<CommitInfo> data;
    private SwipeRefreshLayout refreshLayout;
    private LoadMoreRecyclerView recyclerView;
    private ProgressDialog dialog;
    private CommitAdapter adapter;
    private int page = 1;
    private CommitsPresenter presenter;
    private RepositoryInfo repository;

    public static CommitsFragment newInstance(RepositoryInfo repository) {
        Bundle args = new Bundle();
        args.putSerializable("repository", repository);
        CommitsFragment fragment = new CommitsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_commits, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sr_commits);
        recyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recycler_commit);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new SpaceItemDecoration(8));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        RxBus.getInstance().register(this);
        super.onActivityCreated(savedInstanceState);
        this.repository = (RepositoryInfo) getArguments().getSerializable("repository");
        presenter = new CommitsPresenterImpl(this);
        data = new ArrayList<>();
        adapter = new CommitAdapter(mContext, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnLoadingListener(() -> {
            CommitParams params = new CommitParams();
            params.setPage(page + 1);
            presenter.getCommitsOfRepo(repository.getOwner().getLogin(), repository.getName(), params);
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clearCommits();
                initCommits();
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CommitInfo commitInfo = data.get(position);
                Intent intent = new Intent(mContext, CommitDetailActivity.class);
                intent.putExtra("commitInfo", commitInfo);
                intent.putExtra("repositoryInfo", repository);
                startActivity(intent);
            }
        });
        initCommits();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.getInstance().unRegister(this);
    }

    private void initCommits() {
        clearCommits();
        CommitParams params = new CommitParams();
        params.setPage(1);
        presenter.getCommitsOfRepo(repository.getOwner().getLogin(), repository.getName(), params);
    }

    @Override
    public void showWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(mContext);
        dialog.setMessage(getString(R.string.getting_commits));
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
    public void addCommits(List<CommitInfo> infoList) {
        int oldLen = data.size();
        int addLen = infoList.size();
        data.addAll(infoList);
        if (adapter != null) {
            adapter.setData(data);
            adapter.notifyItemRangeInserted(oldLen, addLen);
        }
    }

    @Override
    public void clearCommits() {
        int oldLen = this.data.size();
        this.data.clear();
        adapter.setData(this.data);
        adapter.notifyItemRangeRemoved(0, oldLen);
    }

    @Override
    public void refreshFinish() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
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

    @Subscriber(tag = "updateCommitInfo")
    private Action1 updateAction = new Action1() {
        @Override
        public void call(Object o) {
            if (presenter != null) {
                initCommits();
            }
        }
    };
}
