package github.cjj.ecusthelper.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2016/9/11
 *
 * @author chenjj2048
 */

/**
 * 新闻版块集合
 */
public class NewsSectionsCollection {
    private static final List<NewsSectionBean> mSections;
    private static NewsSectionsCollection mInstance;

    static {
        mSections = new ArrayList<>();
        mSections.add(new NewsSectionBean("校园要闻", 1));
        mSections.add(new NewsSectionBean("综合新闻", 7));
        mSections.add(new NewsSectionBean("招生就业", 65));
        mSections.add(new NewsSectionBean("合作交流", 38));
        mSections.add(new NewsSectionBean("深度报道", 60));
        mSections.add(new NewsSectionBean("图说华理", 68));
        mSections.add(new NewsSectionBean("媒体华理", 21));
    }

    public static NewsSectionsCollection getInstance() {
        return mInstance == null ? (mInstance = new NewsSectionsCollection()) : mInstance;
    }

    public int getSize() {
        return mSections.size();
    }

    /**
     * 根据特定fragment找到对应新闻版块Bean
     *
     * @param fragment_id ViewPager中fragment的位置
     * @return 单一特定新闻版块Bean
     */
    public NewsSectionBean getNewsSection(int fragment_id) {
        return mSections.get(fragment_id);
    }
}
