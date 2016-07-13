package joe.andhuber.user.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import joe.andhuber.R;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class UserMainPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    private Context mContext;
    private int[] titleIds = new int[]{R.string.tab_1_repositories, R.string.tab_2_starred};

    public UserMainPagerAdapter(FragmentManager fm, Context context, ArrayList<Fragment> fragments) {
        super(fm);
        this.mContext = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(titleIds[position]);
    }
}
