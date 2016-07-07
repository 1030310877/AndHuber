package joe.andhuber.main.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import joe.andhuber.R;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<RepositoryInfo> data;
    private Context mContext;

    public RepositoryAdapter(Context mContext, List<RepositoryInfo> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_repositories, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        if (position >= 0 && position < data.size()) {
            RepositoryInfo info = data.get(position);
            holder.setName(info.getName());
            holder.setDescriptionTv(info.getDescription());
            holder.setStarNum(info.getStargazers_count());
            holder.setForkNum(info.getForks_count());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<RepositoryInfo> getData() {
        return data;
    }

    public void setData(List<RepositoryInfo> data) {
        this.data = data;
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView starTv;
        AppCompatTextView forkTv;
        AppCompatTextView nameTv;
        AppCompatTextView descriptionTv;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            starTv = (AppCompatTextView) itemView.findViewById(R.id.text_item_repository_star);
            forkTv = (AppCompatTextView) itemView.findViewById(R.id.text_item_repository_fork);
            nameTv = (AppCompatTextView) itemView.findViewById(R.id.text_item_repository_name);
            descriptionTv = (AppCompatTextView) itemView.findViewById(R.id.text_item_repository_description);
        }

        public void setName(String name) {
            if (!TextUtils.isEmpty(name)) {
                nameTv.setText(name);
            }
        }

        public void setDescriptionTv(String description) {
            if (!TextUtils.isEmpty(description)) {
                descriptionTv.setText(description);
            }
        }

        public void setStarNum(int num) {
            starTv.setText(String.valueOf(num));
        }

        public void setForkNum(int num) {
            forkTv.setText(String.valueOf(num));
        }
    }
}
