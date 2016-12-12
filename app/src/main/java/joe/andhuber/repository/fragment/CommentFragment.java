package joe.andhuber.repository.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import joe.andhuber.R;
import joe.andhuber.base.BaseFragment;
import joe.andhuber.base.BaseParam;
import joe.andhuber.repository.adapter.CommentAdapter;
import joe.andhuber.repository.presenter.CommentPresenter;
import joe.andhuber.repository.presenter.CommentPresenterImpl;
import joe.andhuber.repository.view.CommentView;
import joe.githubapi.model.repositories.CommentInfo;
import joe.githubapi.model.repositories.CommitInfo;
import joe.githubapi.model.repositories.RepositoryInfo;
import joe.view.recyclerview.LoadMoreRecyclerView;

/**
 * Description
 * Created by chenqiao on 2016/8/23.
 */
public class CommentFragment extends BaseFragment implements CommentView {

    private LoadMoreRecyclerView recyclerView;
    private FloatingActionButton addButton;
    private SwipeRefreshLayout refreshLayout;
    private CommentPresenter presenter;
    private ArrayList<CommentInfo> info;
    private CommentAdapter adapter;
    private int page = 1;
    private CommitInfo commitInfo;
    private RepositoryInfo repositoryInfo;
    private ProgressDialog dialog;

    public static CommentFragment newInstance(RepositoryInfo repositoryInfo, CommitInfo commitinfo) {
        Bundle args = new Bundle();
        args.putSerializable("commitInfo", commitinfo);
        args.putSerializable("repositoryInfo", repositoryInfo);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sr_comment);
        recyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recycler_comment);
        addButton = (FloatingActionButton) view.findViewById(R.id.fab_comment);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        commitInfo = (CommitInfo) getArguments().getSerializable("commitInfo");
        repositoryInfo = (RepositoryInfo) getArguments().getSerializable("repositoryInfo");
        info = new ArrayList<>();
        presenter = new CommentPresenterImpl(this);
        adapter = new CommentAdapter(info);
        recyclerView.setAdapter(adapter);
        initComments();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                TextInputLayout inputLayout = new TextInputLayout(getContext());
                inputLayout.setPadding(16, 8, 16, 8);
                TextInputEditText editText = new TextInputEditText(getContext());
                inputLayout.addView(editText);
                inputLayout.setHint(getString(R.string.input_comment));
                builder.setView(inputLayout).setTitle("  ")
                        .setCancelable(true).setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        presenter.createACommentForACommit(repositoryInfo.getOwner().getLogin(), repositoryInfo.getName(), commitInfo.getSha(), editText.getEditableText().toString());
                    }
                }).show();
            }
        });
        recyclerView.setOnLoadingListener(new LoadMoreRecyclerView.onLoadingMoreListener() {
            @Override
            public void onLoading() {
                BaseParam params = new BaseParam();
                params.setPage(page + 1);
                presenter.getCommentsForACommit(repositoryInfo.getOwner().getLogin(), repositoryInfo.getName(), commitInfo.getSha(), params);
            }
        });
    }

    private void initComments() {
        clearComments();
        BaseParam params = new BaseParam();
        params.setPage(1);
        presenter.getCommentsForACommit(repositoryInfo.getOwner().getLogin(), repositoryInfo.getName(), commitInfo.getSha(), params);
    }

    @Override
    public void showWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.submit_comment));
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void dismissWaitDialog() {
        if (dialog != null && getActivity() != null && getActivity().isFinishing() && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void addComments(List<CommentInfo> infoList) {
        int oldLen = info.size();
        int addLen = infoList.size();
        info.addAll(infoList);
        if (adapter != null) {
            adapter.setData(info);
            adapter.notifyItemRangeInserted(oldLen, addLen);
        }
    }

    @Override
    public void clearComments() {
        int oldLen = this.info.size();
        this.info.clear();
        adapter.setData(this.info);
        adapter.notifyItemRangeRemoved(0, oldLen);
    }

    @Override
    public void refreshFinish() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void loadFinish(String text) {
        if (recyclerView.isLoading) {
            recyclerView.loadFinished(text);
        }
    }

    public static class BottomMenuFragment extends BottomSheetDialogFragment {

        private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        };

        @Override
        public void setupDialog(Dialog dialog, int style) {
            super.setupDialog(dialog, style);
            View contentView = View.inflate(getContext(), R.layout.fragment_comment_bottom_menu, null);
            dialog.setContentView(contentView);

            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
            CoordinatorLayout.Behavior behavior = params.getBehavior();

            if (behavior != null && behavior instanceof BottomSheetBehavior) {
                ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
            }
        }
    }
}
