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

import java.util.ArrayList;
import java.util.List;

import joe.andhuber.R;
import joe.andhuber.base.BaseFragment;
import joe.andhuber.search.adapter.SearchUserAdapter;
import joe.andhuber.search.presenter.SearchUsersPresenter;
import joe.andhuber.search.presenter.SearchUsersPresenterImpl;
import joe.andhuber.search.view.SearchUsersView;
import joe.andhuber.user.UserMainActivity;
import joe.githubapi.model.user.UserInfo;
import joe.view.recyclerview.LoadMoreRecyclerView;
import joe.view.recyclerview.OnItemClickListener;
import joe.view.recyclerview.SpaceItemDecoration;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public class SearchUsersFragment extends BaseFragment implements SearchUsersView {

    private AppCompatSpinner languagesSpinner, sortSpinner;
    private LoadMoreRecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private SearchUserAdapter adapter;
    private ArrayList<UserInfo> users;
    private SearchUsersPresenter presenter;
    private int page = 1;
    private ProgressDialog dialog;
    private String query;

    public static SearchUsersFragment newInstance(String q) {
        Bundle args = new Bundle();
        args.putString("query", q);
        SearchUsersFragment fragment = new SearchUsersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_users, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        languagesSpinner = (AppCompatSpinner) view.findViewById(R.id.spinner_search_language);
        sortSpinner = (AppCompatSpinner) view.findViewById(R.id.spinner_search_sort);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sr_search_user);
        recyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recycler_search_user);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new SpaceItemDecoration(16));
        users = new ArrayList<>();
        adapter = new SearchUserAdapter(users);
        recyclerView.setAdapter(adapter);

        presenter = new SearchUsersPresenterImpl(this);
        query = getArguments().getString("query");
        search(page);

        initListeners();
    }

    private void initListeners() {
        languagesSpinner.setOnItemSelectedListener(spinnerListener);
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
                clearUser();
                search(1);
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startToUserMain(users.get(position).getLogin());
            }
        });
    }

    private void startToUserMain(String loginName) {
        Intent intent = new Intent(mContext, UserMainActivity.class);
        intent.putExtra("user", loginName);
        intent.putExtra("isHome", false);
        startActivity(intent);
    }

    private AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            clearUser();
            search(1);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void search(int p) {
        String language = (String) languagesSpinner.getSelectedItem();
        String sort = (String) sortSpinner.getSelectedItem();
        presenter.searchUsers(query, language, sort, p);
    }

    private void clearUser() {
        int len = users.size();
        users.clear();
        adapter.notifyItemRangeRemoved(0, len);
    }

    @Override
    public void showWaitDialog() {
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

    @Override
    public void showUsers(List<UserInfo> users) {
        int oldSize = this.users.size();
        this.users.addAll(users);
        adapter.notifyItemRangeInserted(oldSize, users.size());
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
}
