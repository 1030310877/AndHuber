package joe.andhuber.repository.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joe.andhuber.base.BaseFragment;
import joe.githubapi.model.repositories.CommitInfo;

/**
 * Description
 * Created by chenqiao on 2016/8/23.
 */
public class DiffFragment extends BaseFragment {

    public static DiffFragment newInstance(CommitInfo commitinfo) {
        Bundle args = new Bundle();
        args.putSerializable("commitInfo", commitinfo);
        DiffFragment fragment = new DiffFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
