package joe.andhuber.repository.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

import joe.andhuber.R;
import joe.andhuber.utils.GlideUtils;
import joe.githubapi.model.repositories.CommentInfo;

/**
 * Description
 * Created by chenqiao on 2016/9/2.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<CommentInfo> data;

    public CommentAdapter(ArrayList<CommentInfo> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position >= 0 && position < data.size()) {
            CommentInfo info = data.get(position);
            holder.setHeadImg(info.getUser().getAvatar_url());
            if (!TextUtils.isEmpty(info.getUser().getName())) {
                holder.setName(info.getUser().getName());
            } else {
                holder.setName(info.getUser().getLogin());
            }
            holder.setContent(info.getBody());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<CommentInfo> data) {
        this.data = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView nameTv;
        private ExpandableTextView contentTv;
        private AppCompatImageView headImg;

        public ViewHolder(View itemView) {
            super(itemView);
            headImg = (AppCompatImageView) itemView.findViewById(R.id.img_item_comment_head);
            nameTv = (AppCompatTextView) itemView.findViewById(R.id.tv_item_comment_name);
            contentTv = (ExpandableTextView) itemView.findViewById(R.id.tv_item_comment_content);
        }

        public void setHeadImg(String imgUrl) {
            GlideUtils.loadRoundImageView(headImg.getContext(), imgUrl, headImg);
        }

        public void setName(String name) {
            nameTv.setText(name);
        }

        public void setContent(String content) {
            contentTv.setText(content);
        }
    }
}
