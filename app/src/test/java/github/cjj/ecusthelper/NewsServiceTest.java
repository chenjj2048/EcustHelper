package github.cjj.ecusthelper;

import org.junit.Test;

import java.util.Locale;

import github.cjj.ecusthelper.annotation.BaseUrl;
import github.cjj.ecusthelper.bean.NewsBean;
import github.cjj.ecusthelper.bean.NewsPageParseResult;
import github.cjj.ecusthelper.bean.NewsSectionBean;
import github.cjj.ecusthelper.bean.NewsSectionsCollection;
import github.cjj.ecusthelper.convert.NewsConverterFactory;
import github.cjj.ecusthelper.retrofit.api.NewsService;
import github.cjj.ecusthelper.util.util.MathUtils;
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
    public void testIsAnnotationPresent() {
        Class<NewsService> clazz = NewsService.class;
        BaseUrl annotation = clazz.getAnnotation(BaseUrl.class);
        assertTrue(clazz.isAnnotationPresent(BaseUrl.class));
        assertTrue(annotation.value().startsWith("http://"));
        System.out.println("测试类上注解value：" + annotation.value());
    }

    /**
     * 测试新闻首页数据获取
     */
    @Test
    public void testHomePageNewsObtain() {
        System.out.println("测试首页新闻获取并解析结果...");

        final int page = 1;

        Observable<NewsPageParseResult> observable = getRetrofitNewsService().getNews(page);
        assertNotNull(observable);

        verityParseResult(observable);
    }

    /**
     * 测试其他版块新闻数据获取
     */
    @Test
    public void testOtherSectionNewsObtain() {
        final int page = 1;
        int random_section = MathUtils.constrain(
                (int) (Math.random() * NewsSectionsCollection.getInstance().getSize()),
                1,
                NewsSectionsCollection.getInstance().getSize() - 1);
        NewsSectionBean newsSectionBean = NewsSectionsCollection.getInstance().getNewsSection(random_section);

        System.out.println(String.format(Locale.CHINA,
                "测试 \"%d.%s(id = %d)\" 版块新闻获取并解析结果...",
                random_section, newsSectionBean.sectionTitle, newsSectionBean.sectionId));

        final int category_id = NewsSectionsCollection.getInstance()
                .getNewsSection(random_section)
                .sectionId;

        Observable<NewsPageParseResult> observable = getRetrofitNewsService().getNews(category_id, page);
        assertNotNull(observable);

        verityParseResult(observable);
    }

    private NewsService getRetrofitNewsService() {
        NewsService newsService = RetrofitUtil.create(NewsConverterFactory.create(), NewsService.class);
        assertNotNull(newsService);
        return newsService;
    }

    /**
     * 验证返回的结果是否解析成功
     * 解析出的新闻数量应在 (0,20] 间
     *
     * @param observable 数据来源
     */
    private void verityParseResult(Observable<NewsPageParseResult> observable) {
        observable.flatMap(newsPageParseResult -> Observable.from(newsPageParseResult.getItems()))
                .doOnNext(new Action1<NewsBean>() {
                    int i = 0;

                    @Override
                    public void call(NewsBean newsBean) {
                        assertNotNull(newsBean);

                        String str = String.format(Locale.CHINA, "%d.%s %s %s",
                                ++i, newsBean.getTitle(), newsBean.getTime(), newsBean.getUrl());
                        System.out.println(str);
                    }
                })
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
                        assertTrue(count > 0 && count <= 20);
                    }
                });
    }
}
