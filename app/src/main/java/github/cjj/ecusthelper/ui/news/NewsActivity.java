package github.cjj.ecusthelper.ui.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import github.cjj.ecusthelper.R;
import github.cjj.ecusthelper.adapter.NewsViewPagerAdapter;
import github.cjj.ecusthelper.base.BaseAppCompatActivity;
import github.cjj.ecusthelper.util.util.ToolbarUtil;

public class NewsActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_news;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
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
}
