package github.cjj.ecusthelper.ui.news;

import android.support.v7.widget.RecyclerView;

import github.cjj.ecusthelper.adapter.NewsItemAdapter;

/**
 * Created on 2016/9/5
 *
 * @author chenjj2048
 */
public class NewsPresenter implements NewsContract.Presenter {
    @Override
    public NewsItemAdapter getAdapter() {
        return new NewsItemAdapter();
    }


}
