package github.cjj.ecusthelper;

import org.junit.Test;

import java.util.Locale;

import github.cjj.ecusthelper.bean.NewsBean;
import github.cjj.ecusthelper.convert.NewsConverterFactory;
import github.cjj.ecusthelper.retrofit.NewsService;
import github.cjj.ecusthelper.util.util.RetrofitUtil;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created on 2016/9/7
 *
 * @author chenjj2048
 */
public class NewsServiceTest {

    @Test
    public void testNewsService() throws InterruptedException {
        NewsService newsService = RetrofitUtil.create("http://news.ecust.edu.cn/", NewsConverterFactory.create(), NewsService.class);
        assertNotNull(newsService);

        newsService.getNews(1)
                .flatMap(newsPageParseResult -> Observable.from(newsPageParseResult.getItems()))
                .doOnNext(new Action1<NewsBean>() {
                    int i = 0;

                    @Override
                    public void call(NewsBean newsBean) {
                        String str = String.format(Locale.CHINA, "%d. %s %s", ++i, newsBean.getTitle(), newsBean.getTime());
                        System.out.println(str);
                    }
                })
                .doOnNext(newsBean -> System.out.println(newsBean.getUrl()))
                .count()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        fail();
                    }

                    @Override
                    public void onNext(Integer count) {
                        assertTrue(count > 0);
                    }
                });
    }
}
