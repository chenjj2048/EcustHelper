package github.cjj.ecusthelper.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import github.cjj.ecusthelper.R;

/**
 * Created on 2016/4/5
 *
 * @author chenjj2048
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    private static WeakReference<AppCompatActivity> mLastUsedActivity = new WeakReference<>(null);
    private boolean mStatusbarInstalled = false;

    @Nullable
    public static AppCompatActivity getLastUsedActivity() {
        return mLastUsedActivity.get();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLastUsedActivity = new WeakReference<>(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mStatusbarInstalled) {
            mStatusbarInstalled = true;
            setStatusBar();
        }
    }

    @Override
    public FragmentManager getSupportFragmentManager() {
        return super.getSupportFragmentManager();
    }

    @Override
    public android.app.FragmentManager getFragmentManager() {
        String msg = "请使用V4包的Fragment,避免FragmentStatePagerAdapter和FragmentPagerAdapter不可用";
        throw new UnsupportedOperationException(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @SuppressWarnings("deprecation")
    protected void setStatusBar() {
        final int DEFAULT_ALPHA = 50;
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), DEFAULT_ALPHA);
    }

    @Override
    @CallSuper
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
