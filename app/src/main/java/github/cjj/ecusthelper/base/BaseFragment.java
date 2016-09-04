package github.cjj.ecusthelper.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import github.cjj.ecusthelper.lib.util.Objects;

/**
 * Created on 2016/4/5
 *
 * @author chenjj2048
 */
public abstract class BaseFragment extends Fragment {
    private final Handler mHandler = new Handler();
    private Context context;
    private int mFragmentIndex;

    @Override
    @CallSuper
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = Objects.requireNonNull(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    @CallSuper
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = Objects.requireNonNull(activity);
    }

    @Override
    public Context getContext() {
        return Objects.requireNonNull(context, "请在onAttach后使用");
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mHandler.removeMessages(0);
    }

    public int getFragmentIndex() {
        return mFragmentIndex;
    }

    public void setFragmentIndex(int index) {
        this.mFragmentIndex = index;
    }

    /**
     * 这里可以执行需要上下文的任务，可避免在onAttach()前使用导致context为空的报错
     *
     * @param runnable runnable
     */
    protected void executeRunnable(Runnable runnable) {
        mHandler.postDelayed(() -> {
            if (context != null) {
                runnable.run();
            } else {
                executeRunnable(runnable);
            }
        }, 50);
    }
}
