package github.cjj.ecusthelper.bean;

import java.util.List;

/**
 * Created on 2016/5/12
 *
 * @author chenjj2048
 */

/**
 * 一个页面中所包含的全部相关信息
 * 如：http://news.ecust.edu.cn/news?category_id=7
 */
public class NewsPageParseResult {
    /**
     * 末页位置
     */
    private int endPosition;
    /**
     * 当前页
     */
    private int currentPosition;
    /**
     * 版块标题
     */
    private String sectionTitle;
    /**
     * 一条条的内容数据
     */
    private List<NewsBean> items;

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public List<NewsBean> getItems() {
        return items;
    }

    public void setItems(List<NewsBean> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "NewsPageParseResult{" +
                "endPosition=" + endPosition +
                ", currentPosition=" + currentPosition +
                ", sectionTitle='" + sectionTitle + '\'' +
                ", items=" + items +
                '}';
    }
}