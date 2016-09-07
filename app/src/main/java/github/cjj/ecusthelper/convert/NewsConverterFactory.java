package github.cjj.ecusthelper.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created on 2016/9/6
 * 给Retrofit中addConverterFactory用
 *
 * @author chenjj2048
 */
public class NewsConverterFactory extends Converter.Factory {
    public static NewsConverterFactory create() {
        return new NewsConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return responseBody -> NewsParser.getInstance()
                .apply(responseBody.string());
    }
}
