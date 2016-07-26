package joe.andhuber.search.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import joe.andhuber.R;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/26.
 */
public class SearchReposAdapter extends RecyclerView.Adapter<SearchReposAdapter.SearchReposViewHolder> {

    private ArrayList<RepositoryInfo> repositories;

    public SearchReposAdapter(ArrayList<RepositoryInfo> repositories) {
        this.repositories = repositories;
    }

    @Override
    public SearchReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_repos, parent, false);
        return new SearchReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchReposViewHolder holder, int position) {
        if (position >= 0 && position < repositories.size()) {
            RepositoryInfo info = repositories.get(position);
            holder.setName(info.getFull_name());
            holder.setLanguage(info.getLanguage());
            holder.setUpdateTime(info.getUpdated_at());
            holder.setStarNum(info.getWatchers_count());
            holder.setForkNum(info.getForks_count());
        }
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    class SearchReposViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView nameTxt, languageTxt, updateTimeTxt, starTxt, forkTxt;

        public SearchReposViewHolder(View itemView) {
            super(itemView);
            nameTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_name);
            languageTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_language);
            updateTimeTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_update_time);
            starTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_star);
            forkTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_repos_fork);
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
    }
}
