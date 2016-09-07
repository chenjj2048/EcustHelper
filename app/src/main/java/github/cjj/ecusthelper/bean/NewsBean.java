package github.cjj.ecusthelper.bean;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import github.cjj.ecusthelper.util.util.DateUtil;
import github.cjj.ecusthelper.util.util.Objects;
import github.cjj.ecusthelper.util.util.RelativeTimeUtil;

/**
 * Created on 2016/4/24
 *
 * @author chenjj2048
 */
public final class NewsBean implements Comparable<NewsBean>, Serializable {
    private static final long serialVersionUID = 12305938204985L;

    /**
     * 标题
     */
    private final String mTitle;
    /**
     * 时间
     */
    private final Date mDate;
    /**
     * 网络地址
     */
    private final String mUrl;

    /**
     * @param title 标题
     * @param time  如"2016-08-31"
     * @param url   url
     */
    public NewsBean(String title, String time, String url) {
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

    @Override
    public int compareTo(@NonNull NewsBean another) {
        return this.mDate.compareTo(another.mDate);
    }

    /**
     * @return 几天前等字样
     */
    //Todo:以后修改这个
    public String getRelativeTimeFromNow() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final RelativeTimeUtil relativeDate = new RelativeTimeUtil(dateFormat, false);
        return relativeDate.parseDateAndTime(mDate);
    }
}