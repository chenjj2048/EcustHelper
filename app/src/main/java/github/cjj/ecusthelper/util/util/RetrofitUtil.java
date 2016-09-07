package github.cjj.ecusthelper.util.util;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created on 2016/9/6
 *
 * @author chenjj2048
 */
public class RetrofitUtil {
    public static <T> T create(String baseUrl, Converter.Factory converterFactory, Class<T> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .build();
        return retrofit.create(service);
    }
}
