package joe.andhuber.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}