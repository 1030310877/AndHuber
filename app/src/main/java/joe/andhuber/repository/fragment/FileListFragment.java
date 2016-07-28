package joe.andhuber.repository.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joe.andhuber.R;
import joe.andhuber.base.BaseFragment;

/**
 * Description
 * Created by chenqiao on 2016/7/28.
 */
public class FileListFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_file_list,container);
        return view;
    }
}
