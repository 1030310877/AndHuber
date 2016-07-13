package joe.andhuber.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import joe.andhuber.base.BaseFragment;
import joe.andhuber.user.adapter.StarsAdapter;
import joe.andhuber.user.presenter.UserMainPresenter;
import joe.andhuber.user.view.StarView;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.view.recyclerview.LoadMoreRecyclerView;
import joe.view.recyclerview.SpaceItemDecoration;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class StarFragment extends BaseFragment implements StarView {
    private List<RepositoryInfo> data;
    private LoadMoreRecyclerView recyclerView;
    private StarsAdapter adapter;
    private UserMainPresenter presenter;

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
        data = new ArrayList<>();
        adapter = new StarsAdapter(mContext, data);
        recyclerView.setAdapter(adapter);
    }

    public void setPresenter(UserMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showStars(List<RepositoryInfo> data) {
        this.data = data;
        if (adapter != null) {
            adapter.setData(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void clearStars() {
        this.data.clear();
        adapter.setData(this.data);
        adapter.notifyDataSetChanged();
    }
}
