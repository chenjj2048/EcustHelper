package github.cjj.ecusthelper.util.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created on 2016/9/6
 *
 * @author chenjj2048
 */
public class DialogUtil {
    public static void dialog(Context context, String msg) {
        if (msg != null)
            dialog(context, "日志-总长度：" + msg.length(), msg);
    }

    public static void dialog(Context context, String title, String msg) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(msg))
            return;

        Observable.just(context)
                .filter((para -> para != null))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((context1 -> {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(context1);
                    mBuilder.setTitle(title)
                            .setMessage(msg)
                            .create()
                            .show();
                }));
    }
}
