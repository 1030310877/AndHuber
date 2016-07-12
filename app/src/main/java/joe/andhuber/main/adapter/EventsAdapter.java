package joe.andhuber.main.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import joe.andhuber.R;
import joe.githubapi.model.event.EventInfo;
import joe.githubapi.util.EventType;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_events, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        if (position >= 0 && position < data.size()) {
            holder.setActionType(data.get(position).getType());
            holder.setActorName(data.get(position).getActor().getLogin());
            holder.setActionReceiver(data.get(position).getRepo().getName());
            holder.setDate(data.get(position).getCreated_at());

            switch (data.get(position).getType()) {
                case EventType.FORK_EVENT:
                    holder.setActionReceiver2(data.get(position).getPayload().getForkee().getFull_name());
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public List<EventInfo> getData() {
        return data;
    }

    public void setData(List<EventInfo> data) {
        this.data = data;
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView actionImg;
        AppCompatTextView actorTxt, actionTxt, action2Txt, receiverTxt, receiver2Txt, dateTxt;

        public EventViewHolder(View itemView) {
            super(itemView);
            actionImg = (AppCompatImageView) itemView.findViewById(R.id.img_item_event_logo);
            actorTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_event_actor_name);
            actionTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_event_action);
            receiverTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_event_action_receiver);
            action2Txt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_event_action_2);
            receiver2Txt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_event_action_receiver_2);
            dateTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_event_date);
        }

        public void setActionType(String eventType) {
            switch (eventType) {
                case EventType.WATCH_EVENT:
                    actionImg.setImageResource(R.drawable.ic_stars_black_18dp);
                    actionTxt.setText(R.string.starred);
                    break;
                case EventType.FORK_EVENT:
                    actionImg.setImageResource(R.drawable.ic_call_split_black_18dp);
                    actionTxt.setText(R.string.forked);
                    action2Txt.setText(R.string.to);
                    break;
                case EventType.DOWNLOAD_EVENT:
                    actionImg.setImageResource(R.drawable.ic_file_download_black_18dp);
                    actionTxt.setText(R.string.download);
                    break;
            }
        }

        public void setActorName(String name) {
            actorTxt.setText(name);
        }

        public void setActionReceiver(String receiver) {
            receiverTxt.setText(receiver);
        }

        public void setActionReceiver2(String receiver2) {
            receiver2Txt.setText(receiver2);
        }

        public void setDate(String date) {
            dateTxt.setText(date);
        }
    }
}
