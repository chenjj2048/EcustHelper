package github.cjj.ecusthelper.retrofit.api;

import github.cjj.ecusthelper.annotation.BaseUrl;
import github.cjj.ecusthelper.bean.NewsPageParseResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created on 2016/9/6
 *
 * @author chenjj2048
 */
@BaseUrl("http://news.ecust.edu.cn/")
public interface NewsService {
    /**
     * 获取首页新闻数据
     *
     * @param page 校园要闻 版块 页数
     * @return 网页解析结果
     */
    @GET("news?important=1")
    Observable<NewsPageParseResult> getNews(@Query("page") int page);

    /**
     * 获取其他新闻版块数据
     *
     * @param category_id 版块id
     *                    综合新闻-7
     *                    招生就业-65
     *                    合作交流-38
     *                    深度报道-60
     *                    图说华理-68
     *                    媒体华理-21
     *                    ...
     * @param page        页数
     * @return 网页解析结果
     */
    @GET("news")
    Observable<NewsPageParseResult> getNews(@Query("category_id") int category_id, @Query("page") int page);
}
