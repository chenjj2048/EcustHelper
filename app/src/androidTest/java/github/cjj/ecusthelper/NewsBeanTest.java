package github.cjj.ecusthelper;

import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import github.cjj.ecusthelper.bean.NewsBean;
import github.cjj.ecusthelper.util.util.DateUtil;

/**
 * Created on 2016/9/11
 *
 * @author chenjj2048
 */
public class NewsBeanTest extends TestCase {
    long NOW;

    public void testConstructorException() {
        boolean throw_execption = false;
        try {
            new NewsBean("title", "time", "url");
        } catch (ParseException e) {
            throw_execption = true;
        }
        assertTrue(throw_execption);
    }

    public void testParseBean() throws Exception {
        final String title = "title";
        final String time = "2016-12-25";
        final String url = "http://123.com";
        NewsBean mNewsBean = new NewsBean(title, time, url);

        assertEquals(mNewsBean.getTitle(), title);
        assertEquals(mNewsBean.getTime(), time);
        assertEquals(mNewsBean.getUrl(), url);
    }


    @SuppressWarnings("SimpleDateFormat")
    public void testGetRelativeTimeSpanString() throws Exception {
        NOW = new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-15").getTime();

        //xx前
        assertEquals(new MockNewsBean("2016-08-15").getRelativeTimeSpanString(), "今天");
        assertEquals(new MockNewsBean("2016-08-14").getRelativeTimeSpanString(), "昨天");
        assertEquals(new MockNewsBean("2016-07-15").getRelativeTimeSpanString(), "1个月前");
        assertEquals(new MockNewsBean("2015-08-30").getRelativeTimeSpanString(), "11个月前");
        assertEquals(new MockNewsBean("2015-08-15").getRelativeTimeSpanString(), "1年前");
        assertEquals(new MockNewsBean("2014-08-15").getRelativeTimeSpanString(), "2年前");
    }

    /**
     * 替换掉当中的System.currentTimeMillis()函数
     */
    public class MockNewsBean extends NewsBean {
        public MockNewsBean(String time) throws ParseException {
            super("", time, "");
        }

        @Override
        protected String innerGetRelativeTime() {
            return DateUtil.getRelativeTimeSpanString(getDate().getTime(), NOW);
        }
    }
}
