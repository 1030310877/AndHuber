package joe.andhuber.search.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import joe.andhuber.R;
import joe.githubapi.model.search.TextMatchesBean;
import joe.githubapi.model.user.UserInfo;
import joe.view.recyclerview.OnItemClickListener;

/**
 * Description
 * Created by chenqiao on 2016/7/21.
 */
public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.SearchUserViewHolder> {

    private OnItemClickListener listener;
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
        if (position >= 0 && position < users.size()) {
            holder.setHeadImg(users.get(position).getAvatar_url());
            holder.setLogin(users.get(position).getLogin());
            for (TextMatchesBean bean : users.get(position).getText_matches()) {
                if (bean.getProperty().equals("name")) {
                    holder.setName(bean.getFragment());
                }
                if (bean.getProperty().equals("email")) {
                    holder.setEmail(bean.getFragment());
                }
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(holder.getAdapterPosition());
                    }
                }
            });
        }
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class SearchUserViewHolder extends RecyclerView.ViewHolder {

        CircleImageView headImg;
        AppCompatTextView name, email, login;

        public SearchUserViewHolder(View itemView) {
            super(itemView);
            headImg = (CircleImageView) itemView.findViewById(R.id.img_search_user_head);
            name = (AppCompatTextView) itemView.findViewById(R.id.txt_search_user_name);
            login = (AppCompatTextView) itemView.findViewById(R.id.txt_search_user_login);
            email = (AppCompatTextView) itemView.findViewById(R.id.txt_search_user_email);
        }

        public void setHeadImg(String url) {
            Glide.with(itemView.getContext()).load(url).into(headImg);
        }

        public void setName(String text) {
            if (TextUtils.isEmpty(text)) {
                name.setText("");
                name.setVisibility(View.GONE);
            } else {
                name.setText(text);
                name.setVisibility(View.VISIBLE);
            }
        }

        public void setLogin(String text) {
            login.setText(text);
        }

        public void setEmail(String emailAddress) {
            if (TextUtils.isEmpty(emailAddress)) {
                email.setVisibility(View.GONE);
                email.setText("");
            } else {
                email.setVisibility(View.VISIBLE);
                email.setText(emailAddress);
            }
        }
    }
}
