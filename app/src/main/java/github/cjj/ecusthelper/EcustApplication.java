package github.cjj.ecusthelper;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import github.cjj.ecusthelper.util.util.logUtil;

/**
 * Created on 2016/9/16
 *
 * @author chenjj2048
 */
public class EcustApplication extends Application {
    private static EcustApplication mInstance;

    public static EcustApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        logUtil.init(this);
        LeakCanary.install(this);

    }
}
