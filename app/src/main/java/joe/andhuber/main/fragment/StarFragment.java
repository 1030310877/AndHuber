package joe.andhuber.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import joe.andhuber.base.BaseFragment;
import joe.andhuber.main.adapter.StarsAdapter;
import joe.andhuber.main.view.StarView;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.view.SpaceItemDecoration;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class StarFragment extends BaseFragment implements StarView {
    private List<RepositoryInfo> data;
    private RecyclerView recyclerView;
    private StarsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = new RecyclerView(mContext);
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
