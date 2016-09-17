package github.cjj.ecusthelper.util.util;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import static github.cjj.ecusthelper.util.util.logUtil.PrinterFunc.LEVEL_D;
import static github.cjj.ecusthelper.util.util.logUtil.PrinterFunc.LEVEL_E;
import static github.cjj.ecusthelper.util.util.logUtil.PrinterFunc.LEVEL_I;
import static github.cjj.ecusthelper.util.util.logUtil.PrinterFunc.LEVEL_V;
import static github.cjj.ecusthelper.util.util.logUtil.PrinterFunc.LEVEL_W;

/**
 * Created on 2016/3/21
 *
 * @author chenjj2048
 */
//Todo:如果string长度大于4k，截断字符串分几次打印，否则会打印不全
@SuppressWarnings("JavaDoc")
public final class logUtil {
    private static final String TAG = "logUtil";
    public static boolean enable = true;
    private static Application mApp;

    /**
     * 传入一个应用上下文
     *
     * @param application
     */
    public static void init(Application application) {
        mApp = application;
    }

    public static void v(String msg) {
        if (!enable) return;
        printStr(LEVEL_V, msg);
    }

    public static void d(String msg) {
        if (!enable) return;
        printStr(LEVEL_D, msg);
    }

    public static void i(String msg) {
        if (!enable) return;
        printStr(LEVEL_I, msg);
    }

    public static void w(String msg) {
        if (!enable) return;
        printStr(LEVEL_W, msg);
    }

    public static void e(String msg) {
        if (!enable) return;
        printStr(LEVEL_E, msg);
    }

    /**
     * 用于Debug，测试是否运行到某一处代码
     */
    public static void testHereExectued() {
        printStr(LEVEL_D, "此处已执行");
    }

    /**
     * @param msg   should be singleLine
     * @param level
     */
    private static void printStr(int level, String msg) {
        PrinterFunc func = PrinterFunc.create(level);

        StackTraceElement current = getTargetStackTraceElement(0);
        StackTraceElement parent = getTargetStackTraceElement(1);

        assert current != null;
        assert parent != null;

        printSingleLine(func, "╔════════════════════════════════════════════════════════");
        printSingleLine(func, "║Thread: " + Thread.currentThread().getName() + " (" + current.getFileName().replace(".java", ")"));
        printSingleLine(func, "╟────────────────────────────────────────────────────────");
        printSingleLine(func, "║" + msg);
        printSingleLine(func, "║" + parent.getClassName() + "." + parent.getMethodName() + "(" + parent.getFileName() + ":" + parent.getLineNumber() + ")");
        printSingleLine(func, "║  " + current.getClassName() + "." + current.getMethodName() + "(" + current.getFileName() + ":" + current.getLineNumber() + ")");
        printSingleLine(func, "╚════════════════════════════════════════════════════════");
    }

    private static void printSingleLine(PrinterFunc func, String str) {
        func.print(TAG, str);
    }

    /**
     * 获取函数调用的堆栈，排除掉系统内部及第三方类
     *
     * @param i 0,1,2,3,...
     * @return
     */
    private static StackTraceElement getTargetStackTraceElement(int i) {
        int skip = 3;
        int found = 0;
        String packageName = mApp.getPackageName();

        for (StackTraceElement stack : Thread.currentThread().getStackTrace()) {
            if (!stack.getClassName().contains(packageName)) continue;
            if (skip-- > 0) continue;
            if (i == found++) return stack;
        }
        return null;
    }

    /**
     * 打印类
     */
    static class PrinterFunc {
        public static final int LEVEL_V = 1;
        public static final int LEVEL_D = 2;
        public static final int LEVEL_I = 3;
        public static final int LEVEL_W = 4;
        public static final int LEVEL_E = 5;
        private static SparseArray<PrinterFunc> mArray = new SparseArray<>();
        private int type;

        /**
         * @param type 日志等级
         * @return
         */
        public static PrinterFunc create(int type) {
            PrinterFunc mFunc = mArray.get(type);

            if (mFunc == null) {
                mFunc = new PrinterFunc();
                mFunc.type = type;
                mArray.put(type, mFunc);
            }
            return mFunc;
        }

        public void print(String tag, String msg) {
            tag = TextUtils.isEmpty(tag) ? "Empty-Tag" : tag;
            msg = TextUtils.isEmpty(msg) ? "Empty-Msg" : msg;
            switch (type) {
                case LEVEL_V:
                    Log.v(tag, msg);
                    break;
                case LEVEL_D:
                    Log.d(tag, msg);
                    break;
                case LEVEL_I:
                    Log.i(tag, msg);
                    break;
                case LEVEL_W:
                    Log.w(tag, msg);
                    break;
                case LEVEL_E:
                    Log.e(tag, msg);
                    break;
            }
        }
    }
}