package joe.andhuber.repository.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import joe.andhuber.R;
import joe.andhuber.base.BaseFragment;
import joe.andhuber.repository.activity.FileContentActivity;
import joe.andhuber.repository.adapter.RepoFileAdapter;
import joe.andhuber.repository.presenter.FileListPresenter;
import joe.andhuber.repository.presenter.FileListPresenterImpl;
import joe.andhuber.repository.view.FileListView;
import joe.githubapi.model.repositories.ContentInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.view.recyclerview.DividerItemDecoration;

/**
 * Description
 * Created by chenqiao on 2016/7/28.
 */
public class FileListFragment extends BaseFragment implements FileListView {
    private ProgressDialog dialog;
    private ArrayList<ContentInfo> data;
    private RepoFileAdapter adapter;
    private RepositoryInfo repository;
    private FileListPresenter presenter;

    public static FileListFragment newInstance(RepositoryInfo repository) {
        Bundle args = new Bundle();
        args.putSerializable("repository", repository);
        FileListFragment fragment = new FileListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_file_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView filesView = (RecyclerView) view.findViewById(R.id.recycler_file_list);
        if (filesView != null) {
            filesView.setLayoutManager(new LinearLayoutManager(mContext));
            filesView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
            data = new ArrayList<>();
            adapter = new RepoFileAdapter(data);
            filesView.setAdapter(adapter);
            adapter.setOnItemClickListener((v, position) -> {
                if (position - 1 < data.size()) {
                    ContentInfo info = data.get(position - 1);
                    String path = info.getPath();
                    if (info.getType().equals("dir")) {
                        presenter.getFilesByPath(repository.getOwner().getLogin(), repository.getName(), path);
                    } else if (info.getType().equals("file")) {
                        startToContentView(info.getName(), info.getDownload_url(), "");
                    }
                }
            });
            adapter.setOnPathClick(path -> presenter.getFilesByPath(repository.getOwner().getLogin(), repository.getName(), path));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        repository = (RepositoryInfo) getArguments().getSerializable("repository");
        presenter = new FileListPresenterImpl(this);
        presenter.getFilesByPath(repository.getOwner().getLogin(), repository.getName(), "");
    }

    @Override
    public void showPath(List<String> paths) {
        adapter.setNowPath(paths);
        adapter.notifyItemChanged(0);
    }

    @Override
    public void showFiles(List<ContentInfo> files) {
        data.clear();
        data.addAll(files);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void startToContentView(String fileName, String url, String content) {
        Intent intent = new Intent(mContext, FileContentActivity.class);
        intent.putExtra("fileName", fileName);
        intent.putExtra("url", url);
        intent.putExtra("content", content);
        startActivity(intent);
    }

    @Override
    public void showWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(mContext);
        dialog.setMessage(getString(R.string.getting_files));
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void dismissWaitDialog() {
        if (dialog != null && getActivity() != null && !getActivity().isFinishing() && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
