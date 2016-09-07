package github.cjj.ecusthelper.convert;

import android.support.annotation.NonNull;

import com.squareup.haha.guava.base.Function;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import github.cjj.ecusthelper.bean.NewsBean;
import github.cjj.ecusthelper.bean.NewsPageParseResult;
import github.cjj.ecusthelper.util.util.Objects;

/**
 * Created on 2016/5/12
 *
 * @author chenjj2048
 */
public class NewsParser implements Function<String, NewsPageParseResult> {
    private static NewsParser mInstance;

    private NewsParser() {
    }

    /**
     * 单例模式
     *
     * @return instance
     */
    public static NewsParser getInstance() {
        if (mInstance == null)
            mInstance = new NewsParser();
        return mInstance;
    }

    /**
     * 解析网页内容
     *
     * @param sourceCode 网页源代码，如http://news.ecust.edu.cn/news?category_id=7
     * @return 解析结果
     * @throws RuntimeException
     */
    @NonNull
    @Override
    public NewsPageParseResult apply(String sourceCode) {
        Objects.requireNonNull(sourceCode);

        final NewsPageParseResult mResult = new NewsPageParseResult();
        Document doc = Jsoup.parse(sourceCode);
        Element left = doc.getElementsByClass("left").first();

        //1、提取分类标题
        final String sectionTitle = left.getElementsByClass("left_title").text().trim();
        mResult.setSectionTitle(sectionTitle);

        //2、提取新闻条目
        Element content = left.getElementsByClass("content").first();

        Elements collection_li = content.select("ul").first().select("li");
        List<NewsBean> mList = new ArrayList<>(20);
        for (Element li : collection_li) {
            final String title = li.select("span").last().text();
            final String time = li.getElementsByClass("time").first().text();
            final String url = uniformUrl("http://news.ecust.edu.cn" + li.select("a").attr("href"));

            final NewsBean item = new NewsBean(title, time, url);
            mList.add(item);
        }
        mResult.setItems(mList);

        //3、解析当前所在页数
        //4、解析末页的页数
        Element pagination = content.getElementsByClass("pagination").first();
        Objects.requireNonNull(pagination);
        Elements lis = pagination.select("li");
        for (Element li : lis) {
            final String className = li.className();
            if (className.contains("active")) {
                final int currentPage = Integer.parseInt(li.text());
                mResult.setCurrentPosition(currentPage);
            } else if (className.contains("last")) {
                final String lastPageString = li.select("a").first().attr("href");
                final int lastPage = extractPage(lastPageString);
                mResult.setEndPosition(lastPage);
            }
        }
        return mResult;
    }

    /**
     * @param input 类似于"/news?category_id=7&page=760"
     * @return 这个例子里是760
     */
    private int extractPage(String input) {
        String string = input.substring(input.lastIndexOf("=") + 1);
        return Integer.parseInt(string);
    }

    /**
     * 将http://news.ecust.edu.cn/news/35445?important=&category_id=7
     * 统一转为http://news.ecust.edu.cn/news/35445
     * .
     * 地址内容均能正常访问
     */
    private String uniformUrl(String url) {
        Objects.requireNonNull(url);

        int i = url.indexOf("?");
        if (i >= 0)
            url = url.substring(0, i);
        return url;
    }
}