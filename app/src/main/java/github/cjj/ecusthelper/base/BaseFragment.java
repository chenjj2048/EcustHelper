package github.cjj.ecusthelper.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created on 2016/4/5
 *
 * @author chenjj2048
 */
public abstract class BaseFragment extends Fragment {
    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;
    //标志位 fragment是否可见
    protected boolean isVisible;
    private Handler mHandler;

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void finishCreateView(Bundle state);

    protected abstract void lazyLoad();

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public final void onViewCreated(View view, Bundle savedInstanceState) {
        //绑定控件
        ButterKnife.bind(this, view);
        //初始化控件
        finishCreateView(savedInstanceState);
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mHandler != null)
            mHandler.removeMessages(0);
    }

    /**
     * Fragment数据的懒加载
     *
     * @param isVisibleToUser isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        isVisible = getUserVisibleHint();
        if (isVisible)
            onVisible();
        else
            onInvisible();
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    /**
     * 这里可以执行需要上下文的任务，可避免在onAttach()前使用导致context为空的报错
     *
     * @param runnable runnable
     */
    protected final void postRunnable(Runnable runnable) {
        if (mHandler == null) mHandler = new Handler();

        mHandler.postDelayed(() -> {
            if (super.getContext() != null) {
                runnable.run();
            } else {
                postRunnable(runnable);
            }
        }, 50);
    }
}
