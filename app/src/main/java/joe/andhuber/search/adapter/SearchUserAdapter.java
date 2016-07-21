package joe.andhuber.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import joe.andhuber.R;
import joe.githubapi.model.user.UserInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/21.
 */
public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.SearchUserViewHolder> {

    private List<UserInfo> users;

    public SearchUserAdapter(List<UserInfo> users) {
        this.users = users;
    }

    @Override
    public SearchUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_user, parent, false);
        return new SearchUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchUserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    class SearchUserViewHolder extends RecyclerView.ViewHolder {

        public SearchUserViewHolder(View itemView) {
            super(itemView);
        }
    }
}
