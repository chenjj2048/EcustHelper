package github.cjj.ecusthelper;

import junit.framework.TestCase;

import java.util.concurrent.TimeUnit;

import github.cjj.ecusthelper.util.util.DateUtil;

/**
 * Created on 2016/9/11
 *
 * @author chenjj2048
 */
public class DateUtilTest extends TestCase {
    /**
     * 产生一个与当前时间存在差值的新时间
     */
    private long makeTime(int i, TimeUnit timeUnit) {
        return System.currentTimeMillis() + timeUnit.toMillis(i);
    }

    public void testSeconds() throws Exception {
        TimeUnit SECOND = TimeUnit.SECONDS;

        //xx前
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(0, SECOND)), "0 秒前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-1, SECOND)), "1 秒前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-10, SECOND)), "10 秒前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-59, SECOND)), "59 秒前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-60, SECOND)), "1 分钟前");

        //xx后
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(1, SECOND)), "1 秒后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(10, SECOND)), "10 秒后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(59, SECOND)), "59 秒后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(60, SECOND)), "1 分钟后");
    }

    public void testMinutes() throws Exception {
        TimeUnit MINUTE = TimeUnit.MINUTES;

        //xx前
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-1, MINUTE)), "1 分钟前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-10, MINUTE)), "10 分钟前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-59, MINUTE)), "59 分钟前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-60, MINUTE)), "1 小时前");

        //xx后
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(1, MINUTE)), "1 分钟后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(10, MINUTE)), "10 分钟后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(59, MINUTE)), "59 分钟后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(60, MINUTE)), "1 小时后");
    }

    public void testHours() throws Exception {
        TimeUnit HOUR = TimeUnit.HOURS;

        //xx前
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-1, HOUR)), "1 小时前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-10, HOUR)), "10 小时前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-23, HOUR)), "23 小时前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-24, HOUR)), "昨天");

        //xx后
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(1, HOUR)), "1 小时后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(10, HOUR)), "10 小时后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(23, HOUR)), "23 小时后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(24, HOUR)), "明天");
    }

    /**
     * 7天以内时间能解析正确
     * 七天以外时间解析会出错，显示成x月x日
     * assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-7, DAY)), "7 天前");
     * assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-8, DAY)), "8 天前");
     * assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(7, DAY)), "7 天后");
     * assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(8, DAY)), "8 天后");
     */
    public void testDaysIn7Days() throws Exception {
        TimeUnit DAY = TimeUnit.DAYS;

        //xx前
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-1, DAY)), "昨天");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-2, DAY)), "2 天前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-3, DAY)), "3 天前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-6, DAY)), "6 天前");

        //xx后
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(1, DAY)), "明天");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(2, DAY)), "2 天后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(3, DAY)), "3 天后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(6, DAY)), "6 天后");
    }

    /**
     * 7天时间外，采用自定义时间进行解析
     */
    public void testDaysLargeThan7Days() throws Exception {
        TimeUnit DAY = TimeUnit.DAYS;

        //xx前
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-7, DAY)), "7 天前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-8, DAY)), "8 天前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-30, DAY)), "30 天前");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(-31, DAY)), "1 个月前");

        //xx后
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(7, DAY)), "7 天后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(8, DAY)), "8 天后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(30, DAY)), "30 天后");
        assertEquals(DateUtil.getRelativeTimeSpanString(makeTime(31, DAY)), "1 个月后");
    }

    /**
     * 不区分大小月闰年情况一个月28、30、31天
     */
    public void testMonths() throws Exception {
        long DAY = TimeUnit.DAYS.toMillis(1);
        long MONTH = 31 * DAY;

        //xx前
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 30 * DAY), "30 天前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 31 * DAY), "1 个月前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 2 * MONTH), "2 个月前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 11 * MONTH), "11 个月前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 364 * DAY), "11 个月前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 365 * DAY), "1 年前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 366 * DAY), "1 年前");

        //xx后
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 30 * DAY), "30 天后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 31 * DAY), "1 个月后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 2 * MONTH), "2 个月后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 11 * MONTH), "11 个月后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 364 * DAY), "11 个月后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 365 * DAY), "1 年后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 366 * DAY), "1 年后");
    }

    /**
     * 一年以365天计，不单独区分闰年
     */
    public void testYear() throws Exception {
        long YEAR = TimeUnit.DAYS.toMillis(365);

        //xx前
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - YEAR), "1 年前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 2 * YEAR), "2 年前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 5 * YEAR), "5 年前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 10 * YEAR), "10 年前");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() - 100 * YEAR), "100 年前");

        //xx后
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + YEAR), "1 年后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 2 * YEAR), "2 年后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 5 * YEAR), "5 年后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 10 * YEAR), "10 年后");
        assertEquals(DateUtil.getRelativeTimeSpanString(System.currentTimeMillis() + 100 * YEAR), "100 年后");
    }
}
