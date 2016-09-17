package github.cjj.ecusthelper.ui.news;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import butterknife.Bind;
import github.cjj.ecusthelper.R;
import github.cjj.ecusthelper.adapter.NewsItemAdapter;
import github.cjj.ecusthelper.base.BaseMvpFragment;
import github.cjj.ecusthelper.util.util.SizeUtil;

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
    private int fragmentID;

    public static NewsFragment newInstance(int pos) {
        NewsFragment fragment = new NewsFragment();
        fragment.fragmentID = pos;
        return fragment;
    }

    @Override
    protected NewsContract.Presenter createPresenter() {
        return new NewsPresenter(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void finishCreateView(Bundle state) {
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    @Override
    protected void lazyLoad() {
        /**
         * 初次加载数据
         */
        getPresenter().fetchNews();
    }

    @Override
    public int getFragmentId() {
        return fragmentID;
    }

    private void initRecyclerView() {
        final NewsItemAdapter mAdapter = getPresenter().getAdapter();
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
