package joe.andhuber.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import joe.andhuber.R;
import joe.githubapi.model.event.EventInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    private List<EventInfo> data;
    private Context mContext;

    public EventsAdapter(Context mContext, List<EventInfo> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_stars, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        if (position >= 0 && position < data.size()) {
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<EventInfo> getData() {
        return data;
    }

    public void setData(List<EventInfo> data) {
        this.data = data;
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        public EventViewHolder(View itemView) {
            super(itemView);
        }
    }
}
