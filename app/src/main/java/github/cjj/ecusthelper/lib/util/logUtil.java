package github.cjj.ecusthelper.lib.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created on 2016/3/21
 *
 * @author chenjj2048
 */
@SuppressWarnings("unused")
public final class logUtil {
    private static final String TAG_PREFFIX = "from logUtil-";
    private static boolean mLogPrintAllowed = true;

    /**
     * 是否允许打印日志
     *
     * @param allow allow or not
     */
    public static void allowLogPrint(boolean allow) {
        logUtil.mLogPrintAllowed = allow;
    }

    /**
     * 获取Object的类名
     *
     * @param obj class
     * @return 类名
     */
    @NonNull
    private static String getTagNameFromObject(Object obj) {
        Class clazz;
        String result;
        if (obj instanceof Class)
            clazz = (Class) obj;
        else
            clazz = obj.getClass();
        result = clazz.getSimpleName();

        if (TextUtils.isEmpty(result))
            result = "TAG";
        return TAG_PREFFIX + result;
    }

    /**
     * 以自定义TAG打印日志
     */

    public static void v(String tag, String msg) {
        if (!mLogPrintAllowed) return;
        tag = TextUtils.isEmpty(tag) ? "Empty-Tag" : tag;
        msg = TextUtils.isEmpty(msg) ? "Empty-Msg" : msg;
        Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (!mLogPrintAllowed) return;
        tag = TextUtils.isEmpty(tag) ? "Empty-Tag" : tag;
        msg = TextUtils.isEmpty(msg) ? "Empty-Msg" : msg;
        Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (!mLogPrintAllowed) return;
        tag = TextUtils.isEmpty(tag) ? "Empty-Tag" : tag;
        msg = TextUtils.isEmpty(msg) ? "Empty-Msg" : msg;
        Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (!mLogPrintAllowed) return;
        tag = TextUtils.isEmpty(tag) ? "Empty-Tag" : tag;
        msg = TextUtils.isEmpty(msg) ? "Empty-Msg" : msg;
        Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (!mLogPrintAllowed) return;
        tag = TextUtils.isEmpty(tag) ? "Empty-Tag" : tag;
        msg = TextUtils.isEmpty(msg) ? "Empty-Msg" : msg;
        Log.e(tag, msg);
    }

    /**
     * 以类名为TAG打印日志
     */

    public static void v(Object obj, String msg) {
        if (!mLogPrintAllowed) return;
        final String TAG = getTagNameFromObject(obj);
        v(TAG, msg);
    }

    public static void d(Object obj, String msg) {
        if (!mLogPrintAllowed) return;
        final String TAG = getTagNameFromObject(obj);
        d(TAG, msg);
    }

    public static void i(Object obj, String msg) {
        if (!mLogPrintAllowed) return;
        final String TAG = getTagNameFromObject(obj);
        i(TAG, msg);
    }

    public static void w(Object obj, String msg) {
        if (!mLogPrintAllowed) return;
        final String TAG = getTagNameFromObject(obj);
        w(TAG, msg);
    }

    public static void e(Object obj, String msg) {
        if (!mLogPrintAllowed) return;
        final String TAG = getTagNameFromObject(obj);
        e(TAG, msg);
    }
}