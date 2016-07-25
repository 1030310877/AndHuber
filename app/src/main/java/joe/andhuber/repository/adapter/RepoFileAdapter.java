package joe.andhuber.repository.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import joe.andhuber.R;
import joe.githubapi.model.repositories.ContentInfo;
import joe.view.recyclerview.OnItemClickListener;

/**
 * Description
 * Created by chenqiao on 2016/7/18.
 */
public class RepoFileAdapter extends RecyclerView.Adapter<RepoFileAdapter.RepoFileViewHolder> {

    private static final int PATH = 0x00;
    private static final int FILE = 0x01;
    private List<ContentInfo> data;
    private List<String> nowPath;
    private OnItemClickListener listener;
    private OnPathClick pathClick;

    public RepoFileAdapter(List<ContentInfo> data) {
        this.data = data;
        nowPath = new ArrayList<>();
    }

    @Override
    public RepoFileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case PATH:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repodetail_path, parent, false);
                return new RepoFileViewHolder(view, viewType);
            default:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repodetail_file, parent, false);
                return new RepoFileViewHolder(view2, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RepoFileViewHolder holder, int position) {
        if (position >= 0 && position < data.size() + 1) {
            switch (holder.getItemViewType()) {
                case PATH:
                    holder.setPath(nowPath);
                    break;
                case FILE:
                    holder.setType(data.get(position - 1).getType());
                    holder.setFileName(data.get(position - 1).getName());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (listener != null) {
                                listener.onItemClick(holder.getAdapterPosition());
                            }
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return PATH;
        } else {
            return FILE;
        }
    }

    public void setData(List<ContentInfo> data) {
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnPathClick(OnPathClick pathClick) {
        this.pathClick = pathClick;
    }

    public void setNowPath(List<String> nowPath) {
        this.nowPath = nowPath;
    }

    public List<String> getNowPath() {
        return nowPath;
    }

    class RepoFileViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView typeImg;
        AppCompatTextView nameTxt;
        LinearLayout linearLayout;

        public RepoFileViewHolder(View itemView, int type) {
            super(itemView);
            switch (type) {
                case PATH:
                    linearLayout = (LinearLayout) itemView.findViewById(R.id.layout_repodetail_path);
                    break;
                case FILE:
                    typeImg = (AppCompatImageView) itemView.findViewById(R.id.img_item_detail_file_type);
                    nameTxt = (AppCompatTextView) itemView.findViewById(R.id.txt_item_detail_file_name);
                    break;
            }
        }

        public void setPath(List<String> path) {
            if (getItemViewType() == PATH) {
                linearLayout.removeAllViews();
                for (String p : path) {
                    AppCompatTextView textView = new AppCompatTextView(itemView.getContext());
                    if (path.indexOf(p) == 0) {
                        p = "  ..  ";
                    }
                    textView.setText(p + File.separator);
                    textView.setPadding(0, 5, 0, 5);
                    textView.setTextColor(Color.BLUE);
                    textView.setTextSize(18);
                    textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                    textView.getPaint().setAntiAlias(true);

                    linearLayout.addView(textView);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int childCount = linearLayout.getChildCount();
                            StringBuilder builder = new StringBuilder();
                            for (int i = 0; i < childCount; i++) {
                                if (i == 0) {
                                    builder.append("");
                                    if (linearLayout.getChildAt(i) == v) {
                                        break;
                                    } else {
                                        continue;
                                    }
                                }
                                AppCompatTextView pathTxt = (AppCompatTextView) linearLayout.getChildAt(i);
                                String temp = pathTxt.getText().toString();
                                builder.append(temp);
                                if (linearLayout.getChildAt(i) == v) {
                                    break;
                                }
                            }
                            if (pathClick != null) {
                                if (builder.length() == 0) {
                                    pathClick.onPathClick("");
                                } else {
                                    String path = builder.substring(0, builder.length() - 1);
                                    pathClick.onPathClick(path);
                                }
                            }
                        }
                    });
                }
            }
        }

        public void setType(String fileType) {
            if (getItemViewType() == FILE) {
                switch (fileType) {
                    case "file":
                        typeImg.setImageResource(R.drawable.ic_insert_drive_file_black_24dp);
                        break;
                    case "dir":
                        typeImg.setImageResource(R.drawable.ic_folder_black_24dp);
                        break;
                }
            }
        }

        public void setFileName(String name) {
            if (getItemViewType() == FILE) {
                nameTxt.setText(name);
            }
        }
    }

    public interface OnPathClick {
        void onPathClick(String path);
    }
}
