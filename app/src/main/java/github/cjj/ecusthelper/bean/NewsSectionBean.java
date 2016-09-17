package github.cjj.ecusthelper.bean;

/**
 * Created on 2016/9/15
 *
 * @author chenjj2048
 */

import github.cjj.ecusthelper.retrofit.api.NewsService;

/**
 * 单一的新闻版块bean
 */
public class NewsSectionBean {
    /**
     * 版块名称
     */
    public String sectionTitle;
    /**
     * 对应retrofit调用中的版块ID
     * 1)sectionID为1时（首页），对应网址为
     * http://news.ecust.edu.cn/news?important=1
     * 2）其余sectionID，对应网址
     * http://news.ecust.edu.cn/news?category_id={sectionID}
     *
     * @see NewsService
     */
    public int sectionId;

    public NewsSectionBean(String sectionTitle, int sectionId) {
        this.sectionTitle = sectionTitle;
        this.sectionId = sectionId;
    }
}