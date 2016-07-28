package joe.andhuber.search.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import joe.andhuber.R;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.view.recyclerview.OnItemClickListener;

/**
 * Description
 * Created by chenqiao on 2016/7/26.
 */
public class SearchReposAdapter extends RecyclerView.Adapter<SearchReposAdapter.SearchReposViewHolder> {
    private OnItemClickListener listener;
    private ArrayList<RepositoryInfo> repositories;

    private int count = 0;

    public SearchReposAdapter(ArrayList<RepositoryInfo> repositories) {
        this.repositories = repositories;
    }

    @Override
    public SearchReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_repos_header, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_repos, parent, false);
        }
        return new SearchReposViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(SearchReposViewHolder holder, int position) {
        if (position >= 0 && position < repositories.size() + 1) {
            if (position == 0) {
                holder.setTotal(count);
                holder.itemView.setOnClickListener(null);
            } else {
                RepositoryInfo info = repositories.get(position - 1);
                holder.setName(info.getFull_name());
                holder.setLanguage(info.getLanguage());
                holder.setUpdateTime(info.getUpdated_at());
                holder.setStarNum(info.getWatchers_count());
                holder.setForkNum(info.getForks_count());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onItemClick(holder.getAdapterPosition() - 1);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return repositories.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class SearchReposViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView nameTxt, languageTxt, updateTimeTxt, starTxt, forkTxt, countTxt;

        public SearchReposViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 0) {
                countTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_result_count);
            } else {
                nameTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_name);
                languageTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_language);
                updateTimeTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_update_time);
                starTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_star);
                forkTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_fork);
            }
        }

        public void setName(String name) {
            nameTxt.setText(name);
        }

        public void setLanguage(String language) {
            languageTxt.setText(language);
        }

        public void setUpdateTime(String time) {
            updateTimeTxt.setText(time);
        }

        public void setStarNum(int num) {
            starTxt.setText(String.valueOf(num));
        }

        public void setForkNum(int num) {
            forkTxt.setText(String.valueOf(num));
        }

        public void setTotal(int num) {
            countTxt.setText(String.valueOf(num));
        }
    }
}
