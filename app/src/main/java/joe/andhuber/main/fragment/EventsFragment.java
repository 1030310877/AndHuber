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
import joe.andhuber.main.adapter.EventsAdapter;
import joe.andhuber.main.view.EventView;
import joe.githubapi.model.event.EventInfo;
import joe.view.SpaceItemDecoration;

/**
 * Description
 * Created by chenqiao on 2016/7/11.
 */
public class EventsFragment extends BaseFragment implements EventView {

    private List<EventInfo> data;
    private RecyclerView recyclerView;
    private EventsAdapter adapter;

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
        adapter = new EventsAdapter(mContext, data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showEvents(List<EventInfo> data) {
        this.data = data;
        if (adapter != null) {
            adapter.setData(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void clearEvents() {
        this.data.clear();
        adapter.setData(this.data);
        adapter.notifyDataSetChanged();
    }
}
