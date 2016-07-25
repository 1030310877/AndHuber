package joe.andhuber.search.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import joe.andhuber.base.BaseFragment;

/**
 * Description
 * Created by chenqiao on 2016/7/19.
 */
public class SearchReposFragment extends BaseFragment {

    public static SearchReposFragment newInstance(String query) {
        Bundle args = new Bundle();
        args.putString("query", query);
        SearchReposFragment fragment = new SearchReposFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(container.getContext());
        textView.setText("1234566");
        return textView;
    }

}
