package joe.andhuber.search.adapter;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

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
    private int count = 0;

    public SearchUserAdapter(List<UserInfo> users) {
        this.users = users;
    }

    @Override
    public SearchUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_repos_header, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_user, parent, false);
        }
        return new SearchUserViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(SearchUserViewHolder holder, int position) {
        if (position >= 0 && position < users.size() + 1) {
            if (position == 0) {
                holder.setTotal(count);
                holder.itemView.setOnClickListener(null);
            } else {
                UserInfo user = users.get(position - 1);
                holder.setHeadImg(user.getAvatar_url());
                holder.setLogin(user.getLogin());
                for (TextMatchesBean bean : user.getText_matches()) {
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
                            listener.onItemClick(v, holder.getAdapterPosition() - 1);
                        }
                    }
                });
            }
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getItemCount() {
        return users.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
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

        AppCompatImageView headImg;
        AppCompatTextView name, email, login, countTxt;

        public SearchUserViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 0) {
                countTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_search_result_count);
            } else {
                headImg = (AppCompatImageView) itemView.findViewById(R.id.img_search_user_head);
                name = (AppCompatTextView) itemView.findViewById(R.id.txt_search_user_name);
                login = (AppCompatTextView) itemView.findViewById(R.id.txt_search_user_login);
                email = (AppCompatTextView) itemView.findViewById(R.id.txt_search_user_email);
            }
        }

        public void setHeadImg(String url) {
            Glide.with(itemView.getContext()).load(url).asBitmap()
                    .placeholder(R.drawable.head_placeholder)
                    .into(new BitmapImageViewTarget(headImg) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(headImg.getContext().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            headImg.setImageDrawable(circularBitmapDrawable);
                        }
                    });
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

        public void setTotal(int num) {
            countTxt.setText(String.valueOf(num));
        }
    }
}
