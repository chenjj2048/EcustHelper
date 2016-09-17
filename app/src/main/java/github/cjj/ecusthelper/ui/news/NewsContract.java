package github.cjj.ecusthelper.ui.news;

import android.content.Context;

import github.cjj.ecusthelper.adapter.NewsItemAdapter;

/**
 * Created on 2016/8/31
 *
 * @author chenjj2048
 */
public interface NewsContract {
    interface IView {

        Context getContext();
        int getFragmentId();
    }

    interface Presenter {

        NewsItemAdapter getAdapter();

        void fetchNews();
    }
}
