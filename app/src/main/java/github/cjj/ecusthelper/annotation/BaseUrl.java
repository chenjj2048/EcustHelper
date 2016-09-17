package github.cjj.ecusthelper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2016/9/8
 *
 * @author chenjj2048
 */

/**
 * 对应Retrofit中的BaseUrl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BaseUrl {
    String value();
}
