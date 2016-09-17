package github.cjj.ecusthelper.ui.news;

import android.support.annotation.NonNull;

import java.util.List;

import github.cjj.ecusthelper.adapter.NewsItemAdapter;
import github.cjj.ecusthelper.bean.NewsBean;
import github.cjj.ecusthelper.bean.NewsPageParseResult;
import github.cjj.ecusthelper.bean.NewsSectionBean;
import github.cjj.ecusthelper.bean.NewsSectionsCollection;
import github.cjj.ecusthelper.retrofit.RetrofitHelper;
import github.cjj.ecusthelper.util.util.logUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 2016/9/5
 *
 * @author chenjj2048
 */
@SuppressWarnings("JavaDoc")
public class NewsPresenter implements NewsContract.Presenter {
    private List<NewsBean> mData;
    private NewsItemAdapter mAdapter;
    private NewsContract.IView view;

    public NewsPresenter(NewsContract.IView view) {
        this.view = view;
    }

    @Override
    public NewsItemAdapter getAdapter() {
        if (mAdapter == null)
            mAdapter = new NewsItemAdapter();
        return mAdapter;
    }

    /**
     * 刷新最新新闻
     */
    @Override
    public void fetchNews() {
        logUtil.d("fetchNews - " + getNewsSection(view.getFragmentId()).sectionTitle);
        Observable.concat(getNetworkData(1), getDiskCache())
                .first()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewsBean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<NewsBean> newsBean) {
                        //此处onNext调用仅一次
                        mData = newsBean;
                        mAdapter.setData(mData);
                    }
                });
    }

    private NewsSectionBean getNewsSection(int fragmentID) {
        return NewsSectionsCollection.getInstance().getNewsSection(fragmentID);
    }

    /**
     * 获取指定页数的网络数据
     *
     * @param page 页数
     * @return
     */
    @SuppressWarnings("Convert2MethodRef")
    @NonNull
    private Observable<List<NewsBean>> getNetworkData(int page) {
        //根据FragmentId拿到新闻版块信息（标题、对应版块id号）
        NewsSectionBean section = getNewsSection(view.getFragmentId());

        //获取新闻结果
        Observable<NewsPageParseResult> mResult;
        if ("校园要闻".equals(section.sectionTitle)) {
            //校园要闻版块
            mResult = RetrofitHelper.getNewsApi().getNews(page);
        } else {
            //其他版块
            mResult = RetrofitHelper.getNewsApi().getNews(section.sectionId, page);
        }

        if (mResult == null) {
            logUtil.d("网络数据为空");
            return Observable.empty();
        } else {
            logUtil.d("网络数据获取成功");
            return mResult.map(result -> result.getItems());
        }
    }

    /**
     * 获取本地缓存数据
     *
     * @return
     */
    @NonNull
    private Observable<List<NewsBean>> getDiskCache() {
        return Observable.empty();
    }
}
