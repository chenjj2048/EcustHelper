package github.cjj.ecusthelper.ui.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import github.cjj.ecusthelper.R;
import github.cjj.ecusthelper.base.BaseAppCompatActivity;
import github.cjj.ecusthelper.consts.NewsTitleAndUrlConst;
import github.cjj.ecusthelper.util.util.ToolbarUtil;


public class NewsActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //Toolbar
        ToolbarUtil.setupToolbar(this, mToolbar);

        //initFragmentAdapter
        NewsViewPagerAdapter mAdapter = new NewsViewPagerAdapter(getSupportFragmentManager());

        //bind adapter
        mViewPager.setAdapter(mAdapter);

        //setup TabLayout
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 各新闻版块的FragmentAdapter
     */
    private static class NewsViewPagerAdapter extends FragmentStatePagerAdapter {
        private static final int MAX_FRAGMENT_LIMIT = 3;
        private final LruCache<Integer, NewsFragment> mFragmentCache =
                new LruCache<Integer, NewsFragment>(MAX_FRAGMENT_LIMIT) {
                    @Override
                    protected NewsFragment create(Integer key) {
                        return NewsFragment.newInstance(key);
                    }
                };

        public NewsViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return NewsTitleAndUrlConst.getInstance()
                    .getTitle(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentCache.get(position);
        }

        @Override
        public int getCount() {
            return NewsTitleAndUrlConst.getInstance()
                    .getCatalogCount();
        }
    }
}
