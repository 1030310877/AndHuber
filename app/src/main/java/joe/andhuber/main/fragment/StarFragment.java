package joe.andhuber.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import joe.andhuber.R;
import joe.andhuber.base.BaseFragment;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class StarFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout layout = new FrameLayout(mContext);
        AppCompatImageView imageView = new AppCompatImageView(mContext);
        imageView.setImageResource(R.drawable.ic_import_contacts_black_24dp);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(imageView, params);
        return layout;
    }
}
