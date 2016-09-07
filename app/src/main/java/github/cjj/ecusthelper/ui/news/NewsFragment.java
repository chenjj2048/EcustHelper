package github.cjj.ecusthelper.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import butterknife.Bind;
import github.cjj.ecusthelper.R;
import github.cjj.ecusthelper.adapter.NewsItemAdapter;
import github.cjj.ecusthelper.base.BaseMvpFragment;
import github.cjj.ecusthelper.bean.NewsBean;
import github.cjj.ecusthelper.convert.NewsConverterFactory;
import github.cjj.ecusthelper.retrofit.NewsService;
import github.cjj.ecusthelper.util.util.RetrofitUtil;
import github.cjj.ecusthelper.util.util.SizeUtil;
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
public class NewsFragment extends BaseMvpFragment<NewsContract.Presenter> implements NewsContract.IView {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * Index:对应不同新闻版块
     */
    private int fragmentIndex;

    public static NewsFragment newInstance(int pos) {
        NewsFragment fragment = new NewsFragment();
        fragment.fragmentIndex = pos;
        return fragment;
    }

    @Override
    protected NewsContract.Presenter createPresenter() {
        return new NewsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initSwipeRefreshLayout();

        RetrofitUtil.create("http://news.ecust.edu.cn/", NewsConverterFactory.create(), NewsService.class)
                .getNews(1)
                .flatMap(newsPageParseResult1 ->
                        Observable.from(newsPageParseResult1.getItems()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        logUtil.w(this, newsBean.getTitle() + "  " + newsBean.getTime() + " " + newsBean.getUrl());
                        Toast.makeText(getContext(),newsBean.getTitle() + "  " + newsBean.getTime() + " " + newsBean.getUrl(),Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void initRecyclerView() {
        NewsItemAdapter mAdapter = getPresenter().getAdapter();
        mRecyclerView.setLayoutManager(new StickyHeaderLayoutManager());
        mRecyclerView.setAdapter(mAdapter);

//        final RecyclerView view = mRecyclerView;
//        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        final RecyclerView.Adapter mAdapter = getPresenter().getAdapter();
//
//        view.setLayoutManager(mLayoutManager);
//        view.setAdapter(mAdapter);
//        view.setItemAnimator(new DefaultItemAnimator());
//        view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
//        //上拉加载
//        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState != RecyclerView.SCROLL_STATE_IDLE) return;
//                //滑至底部
//                if (mLayoutManager.findLastCompletelyVisibleItemPosition() + 1 != mAdapter.getItemCount())
//                    return;
//                //尚且不足一个页面
//                if (mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) return;
//                // 加载更多
////                getPresenter().getMoreData();
//            }
//        });
    }

    private void initSwipeRefreshLayout() {
        final SwipeRefreshLayout view = mSwipeRefreshLayout;
        view.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light);
        int size = SizeUtil.dp2px(getContext(), 24);
        view.setProgressViewOffset(false, 0, size);
        //下拉刷新
//        view.setOnRefreshListener(() -> getPresenter().pullToRefresh());
    }
}
