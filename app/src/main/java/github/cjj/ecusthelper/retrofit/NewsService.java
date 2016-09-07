package github.cjj.ecusthelper.retrofit;

import github.cjj.ecusthelper.bean.NewsPageParseResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created on 2016/9/6
 *
 * @author chenjj2048
 */
public interface NewsService {

    @GET("news?important=1")
    Observable<NewsPageParseResult> getNews(@Query("page") int page);
}
