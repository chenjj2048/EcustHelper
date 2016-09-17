package github.cjj.ecusthelper.bean;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import github.cjj.ecusthelper.util.util.DateUtil;
import github.cjj.ecusthelper.util.util.Objects;

/**
 * Created on 2016/4/24
 *
 * @author chenjj2048
 */
public class NewsBean implements Comparable<NewsBean>, Serializable {
    private static final long serialVersionUID = 12305938204985L;

    /**
     * 标题
     */
    private final String mTitle;
    /**
     * 网络地址
     */
    private final String mUrl;
    /**
     * 时间
     */
    private Date mDate;

    /**
     * @param title 标题
     * @param time  如"2016-08-31"
     * @param url   url
     */
    public NewsBean(String title, String time, String url) throws ParseException {
        Objects.requireNonNull(title);
        Objects.requireNonNull(time);
        Objects.requireNonNull(url);
        this.mTitle = title;
        this.mUrl = url;
        this.mDate = DateUtil.str2Date(time, "yyyy-MM-dd");
    }

    public String getTitle() {
        return mTitle;
    }

    public String getTime() {
        return DateUtil.date2Str(mDate, "yyyy-MM-dd");
    }

    public String getUrl() {
        return mUrl;
    }

    public Date getDate() {
        return mDate;
    }

    @Override
    public int compareTo(@NonNull NewsBean another) {
        return this.mDate.compareTo(another.mDate);
    }

    /**
     * @return 几天前等字样
     */
    public String getRelativeTimeSpanString() {
        String str = innerGetRelativeTime();
        str = str.contains("秒") ? "今天" : str.replace(" ", "");
        return str;
    }

    /**
     * 可被Mock掉，用于测试
     */
    protected String innerGetRelativeTime() {
        return DateUtil.getRelativeTimeSpanString(mDate.getTime());
    }
}