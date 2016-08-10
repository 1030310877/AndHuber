package joe.andhuber.repository.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import joe.andhuber.R;
import joe.githubapi.model.repositories.CommitInfo;
import joe.view.recyclerview.OnItemClickListener;

/**
 * Description
 * Created by chenqiao on 2016/8/9.
 */
public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.CommitViewHolder> {

    private Context mContext;
    private List<CommitInfo> data;
    private OnItemClickListener listener;

    public CommitAdapter(Context mContext, List<CommitInfo> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public CommitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_commit, parent, false);
        return new CommitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommitViewHolder holder, int position) {
        if (position >= 0 && position < data.size()) {
            CommitInfo info = data.get(position);
            if (info.getCommitter() != null) {
                holder.setHeadImg(info.getCommitter().getAvatar_url());
                holder.setName(info.getCommitter().getLogin());
            } else {
                holder.setName(info.getCommit().getCommitter().getName());
            }
            holder.setMessage(info.getCommit().getMessage());
            holder.setComment(info.getCommit().getComment_count());
            holder.setDate(info.getCommit().getCommitter().getDate());
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public void setData(List<CommitInfo> data) {
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class CommitViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView nameTxt, messageTxt, dateTxt, commentTxt;
        AppCompatImageView headImg;

        public CommitViewHolder(View itemView) {
            super(itemView);
            headImg = (AppCompatImageView) itemView.findViewById(R.id.img_item_commit_head);
            nameTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_commit_name);
            commentTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_commit_comments);
            messageTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_commit_message);
            dateTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_commit_date);
        }

        public void setHeadImg(String url) {
            Glide.with(mContext).load(url).placeholder(R.drawable.head_placeholder).centerCrop().into(headImg);
        }

        public void setName(String name) {
            nameTxt.setText(name);
        }

        public void setComment(int count) {
            commentTxt.setText(String.format(Locale.getDefault(), "%d", count));
        }

        public void setMessage(String message) {
            messageTxt.setText(message);
        }

        public void setDate(String date) {
            dateTxt.setText(date);
        }
    }
}
