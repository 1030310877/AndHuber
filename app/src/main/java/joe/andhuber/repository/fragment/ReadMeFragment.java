package joe.andhuber.repository.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import joe.andhuber.R;
import joe.andhuber.base.BaseFragment;
import joe.andhuber.repository.presenter.ReadMePresenter;
import joe.andhuber.repository.presenter.ReadMePresenterImpl;
import joe.andhuber.repository.view.ReadMeView;
import joe.githubapi.model.repositories.RepositoryInfo;

/**
 * Description
 * Created by chenqiao on 2016/7/28.
 */
public class ReadMeFragment extends BaseFragment implements ReadMeView {

    private WebView contentView;
    private ProgressDialog dialog;
    private ReadMePresenter presenter;
    private RepositoryInfo repository;

    public static ReadMeFragment newInstance(RepositoryInfo repository) {
        Bundle args = new Bundle();
        args.putSerializable("repository", repository);
        ReadMeFragment fragment = new ReadMeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_readme, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView = (WebView) view.findViewById(R.id.wb_readme);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        repository = (RepositoryInfo) getArguments().getSerializable("repository");
        presenter = new ReadMePresenterImpl(this);
        presenter.getReadMe(repository.getOwner().getLogin(), repository.getName());
    }

    @Override
    public void showReadMe(String content) {
        String code = String.format("<body style=\"word-wrap:break-word;\">%s</body>", content);
        contentView.loadDataWithBaseURL(null, code, "text/html", "UTF-8", null);
    }

    @Override
    public void showWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(mContext);
        dialog.setMessage(getString(R.string.getting_information));
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
