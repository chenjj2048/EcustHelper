package github.cjj.ecusthelper.util.util;

import android.support.annotation.NonNull;

import github.cjj.ecusthelper.annotation.BaseUrl;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created on 2016/9/6
 *
 * @author chenjj2048
 */
public class RetrofitUtil {
    private static OkHttpClient mOkHttpClient;

    public static void setClient(OkHttpClient client) {
        mOkHttpClient = client;
    }

    /**
     * 建议使用 create(Converter.Factory converterFactory, Class<T> clazz)代替
     * clazz上加注解
     */
    public static <T> T create(String baseUrl, Converter.Factory converterFactory, Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .build();
        return retrofit.create(clazz);
    }

    public static <T> T create(Converter.Factory converterFactory, Class<T> clazz) {
        String baseUrl = getAnnotationValue(clazz);
        return create(baseUrl, converterFactory, clazz);
    }

    @NonNull
    private static <T> String getAnnotationValue(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(BaseUrl.class))
            throw new NullPointerException("请在interface上添加 @BaseUrl 注解");

        return clazz.getAnnotation(BaseUrl.class).value();
    }
}
