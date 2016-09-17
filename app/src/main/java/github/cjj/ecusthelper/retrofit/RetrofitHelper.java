package github.cjj.ecusthelper.retrofit;

import java.io.File;
import java.util.concurrent.TimeUnit;

import github.cjj.ecusthelper.EcustApplication;
import github.cjj.ecusthelper.convert.NewsConverterFactory;
import github.cjj.ecusthelper.retrofit.api.NewsService;
import github.cjj.ecusthelper.util.util.RetrofitUtil;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created on 2016/9/16
 *
 * @author chenjj2048
 */
public class RetrofitHelper {
    private static volatile OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
        RetrofitUtil.setClient(mOkHttpClient);
    }

    /**
     * 获取华理新闻Api
     *
     * @return
     */
    public static NewsService getNewsApi() {
        return RetrofitUtil.create(NewsConverterFactory.create(), NewsService.class);
    }

    /**
     * 初始化OKHttpClient
     * 设置缓存
     * 设置超时时间
     * 设置打印日志
     * 设置拦截器
     */
    private static void initOkHttpClient() {
        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    Cache cache = new Cache(new File(EcustApplication.getInstance()
                            .getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            //.addInterceptor(interceptor)
                            //.retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            //.addInterceptor(new UserAgentInterceptor())
                            .build();
                }
            }
        }
    }

    /**
     * 添加拦截器
     */
//    static class UserAgentInterceptor implements Interceptor {
//
//        @Override
//        public Response intercept(Interceptor.Chain chain) throws IOException {
//
//            Request originalRequest = chain.request();
//            Request requestWithUserAgent = originalRequest.newBuilder()
//                    .removeHeader("User-Agent")
//                    .addHeader("User-Agent", "")
//                    .build();
//            return chain.proceed(requestWithUserAgent);
//        }
//    }
}
