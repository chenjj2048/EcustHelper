package github.cjj.ecusthelper.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;
import github.cjj.ecusthelper.R;

/**
 * Created on 2016/4/5
 *
 * @author chenjj2048
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    private boolean mStatusbarInstalled = false;

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void initViews(Bundle savedInstanceState);

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutResId());
        //初始化绑定框架
        ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
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
