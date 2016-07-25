package joe.andhuber.search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

import joe.andhuber.R;
import joe.andhuber.search.adapter.SearchResultPagerAdapter;
import joe.andhuber.search.fragment.SearchReposFragment;
import joe.andhuber.search.fragment.SearchUsersFragment;

/**
 * Description
 * Created by chenqiao on 2016/7/14.
 */
public class SearchResultActivity extends AppCompatActivity {

    private SearchView searchView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String q;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initViews();
        initListeners();
    }

    private void initViews() {
        searchView = (SearchView) findViewById(R.id.searchview_result);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_result);
        viewPager = (ViewPager) findViewById(R.id.vp_result);
        searchView.setIconifiedByDefault(false);
        ArrayList<Fragment> fragments = new ArrayList<>();
        handleIntent(getIntent());
        SearchUsersFragment searchUsersFragment = SearchUsersFragment.newInstance(q);
        SearchReposFragment searchReposFragment = SearchReposFragment.newInstance(q);
        fragments.add(searchReposFragment);
        fragments.add(searchUsersFragment);
        SearchResultPagerAdapter adapter = new SearchResultPagerAdapter(getSupportFragmentManager(), this, fragments);
        if (viewPager != null) {
            viewPager.setAdapter(adapter);
        }
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void initListeners() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        });
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            q = query;
            if (searchView != null) {
                searchView.setQuery(query, false);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }
}