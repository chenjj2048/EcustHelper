package github.cjj.ecusthelper.consts;

/**
 * Created on 2016/4/24
 *
 * @author chenjj2048
 */

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 七个版块对应的标题及地址
 */
public class NewsTitleAndUrlConst {
    private static final List<Pair<String, String>> mNewsPages;
    private static NewsTitleAndUrlConst mInstance;

    static {
        mNewsPages = new ArrayList<>();
        mNewsPages.add(new Pair<>("校园要闻", "http://news.ecust.edu.cn/news?important=1"));
        mNewsPages.add(new Pair<>("综合新闻", "http://news.ecust.edu.cn/news?category_id=7"));
        mNewsPages.add(new Pair<>("招生就业", "http://news.ecust.edu.cn/news?category_id=65"));
        mNewsPages.add(new Pair<>("合作交流", "http://news.ecust.edu.cn/news?category_id=38"));
        mNewsPages.add(new Pair<>("深度报道", "http://news.ecust.edu.cn/news?category_id=60"));
        mNewsPages.add(new Pair<>("图说华理", "http://news.ecust.edu.cn/news?category_id=68"));
        mNewsPages.add(new Pair<>("媒体华理", "http://news.ecust.edu.cn/news?category_id=21"));
    }

    public static NewsTitleAndUrlConst getInstance() {
        if (mInstance == null)
            mInstance = new NewsTitleAndUrlConst();
        return mInstance;
    }

    /**
     * 新闻版块数量
     *
     * @return 数量
     */
    public int getCatalogCount() {
        return mNewsPages.size();
    }

    /**
     * 新闻版块标题
     *
     * @param position 下标
     * @return 标题
     */
    public String getTitle(int position) {
        checkRange(position);
        return mNewsPages.get(position).first;
    }

    /**
     * 新闻版块Url地址
     *
     * @param position 下标
     * @return Url地址
     */
    public String getUrl(int position) {
        checkRange(position);
        return mNewsPages.get(position).second;
    }

    /**
     * 下标越界检查
     *
     * @param position 下标
     */
    private void checkRange(int position) {
        if (position < 0 || position >= mNewsPages.size())
            throw new IllegalArgumentException("新闻版块下标不正确，数组越界");
    }
}