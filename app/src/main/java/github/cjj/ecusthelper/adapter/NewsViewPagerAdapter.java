package github.cjj.ecusthelper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.LruCache;

import github.cjj.ecusthelper.bean.NewsSectionsCollection;
import github.cjj.ecusthelper.ui.news.NewsFragment;

/**
 * Created on 2016/9/15
 *
 * @author chenjj2048
 */

/**
 * 各新闻版块的FragmentAdapter
 */
public class NewsViewPagerAdapter extends FragmentStatePagerAdapter {
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
        return NewsSectionsCollection.getInstance().getNewsSection(position).sectionTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentCache.get(position);
    }

    @Override
    public int getCount() {
        return NewsSectionsCollection.getInstance().getSize();
    }
}
