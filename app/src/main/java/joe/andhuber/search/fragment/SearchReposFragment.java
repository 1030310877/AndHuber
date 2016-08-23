package joe.andhuber.search.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.joe.rxbus.RxBus;
import com.joe.rxbus.ThreadMode;
import com.joe.rxbus.annotation.Subscriber;

import java.util.ArrayList;
import java.util.List;

import joe.andhuber.R;
import joe.andhuber.base.BaseFragment;
import joe.andhuber.repository.activity.RepoDetailActivity;
import joe.andhuber.search.adapter.SearchReposAdapter;
import joe.andhuber.search.presenter.SearchReposPresenter;
import joe.andhuber.search.presenter.SearchReposPresenterImpl;
import joe.andhuber.search.view.SearchReposView;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.view.recyclerview.LoadMoreRecyclerView;
import joe.view.recyclerview.OnItemClickListener;
import joe.view.recyclerview.SpaceItemDecoration;
import rx.functions.Action1;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public class SearchReposFragment extends BaseFragment implements SearchReposView {

    private AppCompatSpinner languageSpinner, sortSpinner;
    private SwipeRefreshLayout refreshLayout;
    private LoadMoreRecyclerView recyclerView;
    private ArrayList<RepositoryInfo> repositories;
    private SearchReposAdapter adapter;
    private ProgressDialog dialog;
    private int page = 1;
    private String query;
    private SearchReposPresenter presenter;

    public static SearchReposFragment newInstance(String query) {
        Bundle args = new Bundle();
        args.putString("query", query);
        SearchReposFragment fragment = new SearchReposFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_repos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        languageSpinner = (AppCompatSpinner) view.findViewById(R.id.spinner_search_language);
        sortSpinner = (AppCompatSpinner) view.findViewById(R.id.spinner_search_sort);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sr_search_repos);
        recyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recycler_search_repos);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new SpaceItemDecoration(16));
        repositories = new ArrayList<>();
        adapter = new SearchReposAdapter(repositories);
        recyclerView.setAdapter(adapter);

        presenter = new SearchReposPresenterImpl(this);
        query = getArguments().getString("query");

        initListeners();
        RxBus.getInstance().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(this);
    }

    private void initListeners() {
        languageSpinner.setOnItemSelectedListener(spinnerListener);
        sortSpinner.setOnItemSelectedListener(spinnerListener);
        recyclerView.setOnLoadingListener(new LoadMoreRecyclerView.onLoadingMoreListener() {
            @Override
            public void onLoading() {
                search(page + 1);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clearRepositories();
                search(1);
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                startToRepositoryDetail(repositories.get(position));
            }
        });
    }

    private void startToRepositoryDetail(RepositoryInfo info) {
        Intent intent = new Intent(mContext, RepoDetailActivity.class);
        intent.putExtra("repository", info);
        startActivity(intent);
    }

    @Subscriber(tag = "reSearch", mode = ThreadMode.MAIN)
    private Action1 reSearch = new Action1() {
        @Override
        public void call(Object o) {
            query = (String) o;
            clearRepositories();
            search(1);
        }
    };

    private AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            clearRepositories();
            search(1);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void clearRepositories() {
        int len = repositories.size();
        repositories.clear();
        adapter.notifyItemRangeRemoved(0, len);
    }

    private void search(int p) {
        String language = (String) languageSpinner.getSelectedItem();
        String sort = (String) sortSpinner.getSelectedItem();
        presenter.searchRepositories(query, language, sort, p);
    }

    @Override
    public void showRepositories(List<RepositoryInfo> repositories) {
        int oldSize = this.repositories.size();
        this.repositories.addAll(repositories);
        adapter.notifyItemRangeInserted(oldSize, repositories.size());
    }

    @Override
    public void loadFinished(String text) {
        recyclerView.loadFinished(text);
    }

    @Override
    public void refreshFinish() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void setTotal(int total) {
        adapter.setCount(total);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new ProgressDialog(mContext);
        dialog.setMessage(getString(R.string.searching));
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
}
