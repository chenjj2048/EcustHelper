package github.cjj.ecusthelper;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import github.cjj.ecusthelper.adapter.NewsItemAdapter;
import github.cjj.ecusthelper.bean.NewsBean;

/**
 * Created on 2016/9/11
 *
 * @author chenjj2048
 */
public class NewsItemAdapterTest extends TestCase {
    List<NewsBean> mRawData;

    @Override
    public void setUp() throws Exception {
        mRawData = new ArrayList<>();
        mRawData.add(new NewsBean("title 1", "2016-01-01", "http://1.com"));
        mRawData.add(new NewsBean("title 2", "2016-01-02", "http://2.com"));
        mRawData.add(new NewsBean("title 3", "2016-03-01", "http://3.com"));
    }

}
