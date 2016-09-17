package github.cjj.ecusthelper.util.util;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 日期操作工具类
 */
@SuppressWarnings({"SimpleDateFormat", "unused"})
public class DateUtil {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间字符串转换成Date
     */
    public static Date str2Date(String str, String format) throws ParseException {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static Date str2Date(String str) throws ParseException {
        return str2Date(str, null);
    }

    /**
     * 将时间字符串转换成Calendar
     *
     * @param str    str
     * @param format format
     * @return Calendar
     */
    public static Calendar str2Calendar(String str, String format) throws ParseException {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    public static Calendar str2Calendar(String str) throws ParseException {
        return str2Calendar(str, null);
    }

    public static String calendar2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return calendar2Str(c, null);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String calendar2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d, String format) {
        // yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    public static Calendar date2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获得当前日期的字符串格式
     * 2016-05-01
     *
     * @return 如2016-05-01
     */
    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH) + "-"
                + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
                + ":" + c.get(Calendar.SECOND);
    }

    /**
     * 获得当前日期的字符串格式
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return calendar2Str(c, format);
    }

    /**
     * 获得当前日期的字符串格式,格式到秒
     *
     * @return time -> yyyy-MM-dd-HH-mm-ss
     */
    public static String getMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }

    /**
     * 格式到天
     *
     * @return time -> yyyy-MM-dd
     */
    public static String getDay(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 格式到毫秒
     *
     * @return time -> yyyy-MM-dd-HH-mm-ss-SSS
     */
    public static String getSMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);
    }

    /**
     * 输入的是String，格式诸如20120102，实现加一天的功能，返回的格式为String，诸如20120103
     */
    public static String strAddOneDay(String str) throws ParseException {
        String year = str.substring(0, 4);
        String month = str.substring(4, 6);
        String day = str.substring(6);
        String date1 = year + "-" + month + "-" + day;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(date1);
        Calendar cd = Calendar.getInstance();
        cd.setTime(startDate);
        cd.add(Calendar.DATE, 1);
        String dateStr = sdf.format(cd.getTime());
        String year1 = dateStr.substring(0, 4);
        String month1 = dateStr.substring(5, 7);
        String day1 = dateStr.substring(8);
        return year1 + month1 + day1;
    }

    /**
     * 输入的是String，格式诸如20120102，实现减一天的功能，返回的格式为String，诸如20120101
     */
    public static String strDecreaseOneDay(String row) throws ParseException {
        String year = row.substring(0, 4);
        String month = row.substring(4, 6);
        String day = row.substring(6);
        String date1 = year + "-" + month + "-" + day;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(date1);
        Calendar cd = Calendar.getInstance();
        cd.setTime(startDate);
        cd.add(Calendar.DATE, -1);
        String dateStr = sdf.format(cd.getTime());
        String year1 = dateStr.substring(0, 4);
        String month1 = dateStr.substring(5, 7);
        String day1 = dateStr.substring(8);
        return year1 + month1 + day1;
    }

    /**
     * 输入的格式为String，诸如20120101，返回的格式为String，诸如2012-01-01
     */
    public static String stringDateChange(String date) {
        if (date.length() == "20120101".length()) {
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6);
            return year + "-" + month + "-" + day;
        } else {
            return date;
        }
    }

    /**
     * 获取昨天 Data
     *
     * @param date date
     * @return Last Date
     */
    public static Date getLastdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取明天Date
     *
     * @param date date
     * @return Next Date
     */
    public static Date getNextdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 判断是否是同一天
     *
     * @param one     one
     * @param another another
     * @return 同一天
     */
    public static boolean isTheSameDay(Date one, Date another) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(one);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(another);
        int oneDay = calendar.get(Calendar.DAY_OF_YEAR);
        int anotherDay = calendar2.get(Calendar.DAY_OF_YEAR);
        return oneDay == anotherDay;
    }

    /**
     * 返回几秒前，几天前，几天后等字样
     *
     * @param time time
     * @param now  now
     * @return 时间差字符串
     * @See DateUtilTest
     */
    public static String getRelativeTimeSpanString(long time, long now) {
        String relativeTimeSpan;
        long days = (time - now) / TimeUnit.DAYS.toMillis(1);

        if (days > -7 && days < 7) {
            //7天时间内用系统类进行解析
            relativeTimeSpan = DateUtils.getRelativeTimeSpanString(
                    time,
                    now,
                    0,  //Pass one of 0,MINUTE_IN_MILLIS, HOUR_IN_MILLIS, DAY_IN_MILLIS,WEEK_IN_MILLIS
                    DateUtils.FORMAT_ABBREV_RELATIVE)
                    .toString();
        } else {
            //7天时间以外用其他方式解析
            days = days > 0 ? days : -days;
            if (days <= 30)
                relativeTimeSpan = days + " 天";
            else if (days < 365)
                relativeTimeSpan = (days / 31) + " 个月";
            else
                relativeTimeSpan = (days / 365) + " 年";

            relativeTimeSpan += (time < now) ? "前" : "后";
        }
        return relativeTimeSpan;
    }

    /**
     * 返回几秒前，几天前，几天后等字样
     *
     * @param time time
     * @return 时间差字符串
     * @See DateUtilTest
     */
    public static String getRelativeTimeSpanString(long time) {
        return getRelativeTimeSpanString(time, System.currentTimeMillis());
    }
}