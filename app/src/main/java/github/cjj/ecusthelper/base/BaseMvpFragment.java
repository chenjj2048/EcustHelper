package github.cjj.ecusthelper.base;

import android.support.annotation.NonNull;

import github.cjj.ecusthelper.lib.util.Objects;


/**
 * Created on 2016/5/10
 *
 * @author chenjj2048
 */
public abstract class BaseMvpFragment<P> extends BaseFragment {
    private P mPresenter;

    @NonNull
    protected P getPresenter() {
        if (mPresenter == null)
            mPresenter = createPresenter();
        return Objects.requireNonNull(mPresenter);
    }

    protected void setPresenter(@NonNull P mPresenter) {
        this.mPresenter = Objects.requireNonNull(mPresenter);
    }

    protected abstract P createPresenter();
}
