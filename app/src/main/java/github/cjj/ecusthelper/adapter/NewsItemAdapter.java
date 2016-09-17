package github.cjj.ecusthelper.adapter;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.cjj.ecusthelper.R;
import github.cjj.ecusthelper.bean.NewsBean;
import github.cjj.ecusthelper.util.util.DateUtil;
import github.cjj.ecusthelper.util.util.logUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created on 2016/9/5
 *
 * @author chenjj2048
 */
@SuppressWarnings("JavaDoc")
public class NewsItemAdapter extends SectioningAdapter {
    /**
     * 经转换后添加头部的数据集
     */
    private List<MonthSection> mMonthSections;

    /**
     * 设置新数据,并通知更新
     *
     * @param sortedData
     */
    public void setData(List<NewsBean> sortedData) {
        if (sortedData == null) return;

        final List<MonthSection> result = new ArrayList<>();

        logUtil.d("数据更新");

        //数据分组并转换类型
        Observable.from(sortedData)
                .groupBy(newsBean1 -> {
                    //按年、月分组
                    Calendar calendar = DateUtil.date2Calendar(newsBean1.getDate());
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    return year + "年" + month + "月";
                })
                .subscribe(monthGroup -> {
                    monthGroup.subscribe(new Subscriber<NewsBean>() {
                        @Override
                        public void onCompleted() {
                            //刷新数据
                            mMonthSections = result;
                            notifyAllSectionsDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(NewsBean newsBean) {
                            //分组标题
                            String headTitle = monthGroup.getKey();

                            //是否已经存在分组
                            MonthSection currentSection = null;
                            for (MonthSection monthSection : result) {
                                if (!headTitle.equals(monthSection.headTitle)) continue;
                                currentSection = monthSection;
                                break;
                            }
                            if (currentSection == null) {
                                //新建分组并加入集合中
                                currentSection = new MonthSection();
                                currentSection.headTitle = monthGroup.getKey();
                                result.add(currentSection);
                            }
                            currentSection.mList.add(newsBean);
                        }
                    });
                });
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        return NewsItemViewHolder.create(parent);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType) {
        MonthSection mSection = mMonthSections.get(sectionIndex);

        if (viewHolder instanceof NewsItemViewHolder) {
            final NewsBean item = mSection.mList.get(itemIndex);

            NewsItemViewHolder holder = (NewsItemViewHolder) viewHolder;
            holder.mTitle.setText(item.getTitle());
            holder.mTime.setText(item.getRelativeTimeSpanString());

        }
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        return HeadHolder.create(parent);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int sectionIndex, int headerUserType) {
        /**
         * 设置头部
         */
        MonthSection monthSection = mMonthSections.get(sectionIndex);
        String headTitle = String.format(Locale.CHINA, "%s  共 %d 条", monthSection.headTitle, monthSection.getSize());
        HeadHolder holder = (HeadHolder) viewHolder;
        holder.textView.setText(headTitle);
    }

    @Override
    public int getNumberOfSections() {
        return (mMonthSections == null) ? 0 : mMonthSections.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        MonthSection mSection = mMonthSections.get(sectionIndex);
        return mSection.mList == null ? 0 : mSection.mList.size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    /**
     * 内容项
     */
    public static class NewsItemViewHolder extends SectioningAdapter.ItemViewHolder {
        @Bind(R.id.title)
        TextView mTitle;

        @Bind(R.id.time)
        TextView mTime;

        @Bind(R.id.pictureTextDrawable)
        ImageView mImageView;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener((v) -> {
//
//            });
        }

        public static NewsItemViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news_description, parent, false);
            return new NewsItemViewHolder(view);
        }
    }

    /**
     * 顶部Head，包含年、月信息
     */
    public static class HeadHolder extends SectioningAdapter.HeaderViewHolder {
        @Bind(android.R.id.text1)
        TextView textView;

        public HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            textView.setBackgroundResource(R.color.bgColorGray);
            textView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.textColorGray));
        }

        public static HeadHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new HeadHolder(view);
        }
    }

    /**
     * 对应一个月内的所有消息
     */
    private static class MonthSection {
        /**
         * 年、月份
         */
        String headTitle;
        /**
         * 一个月内的所有数据
         */
        List<NewsBean> mList = new ArrayList<>();

        int getSize() {
            return mList == null ? 0 : mList.size();
        }
    }
}
