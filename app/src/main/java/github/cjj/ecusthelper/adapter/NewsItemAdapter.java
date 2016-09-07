package github.cjj.ecusthelper.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.cjj.ecusthelper.R;

/**
 * Created on 2016/9/5
 *
 * @author chenjj2048
 */
public class NewsItemAdapter extends SectioningAdapter {
    ArrayList<Section> mSections;

    public NewsItemAdapter() {
        super();
        mSections = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Section section = new Section();
            list.add(i + "月" + i + "日");
            section.month = i + "月";
            section.mList = (ArrayList<String>) list.clone();
            mSections.add(section);
        }
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_news_description, parent, false);
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType) {
        if (viewHolder instanceof NewsItemViewHolder) {
            NewsItemViewHolder holder = (NewsItemViewHolder) viewHolder;
            holder.tvTitle.setText(mSections.get(sectionIndex).mList.get(itemIndex));
            holder.tvTime.setText(mSections.get(sectionIndex).month);
        }
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new HeadHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int sectionIndex, int headerUserType) {
        HeadHolder holder = (HeadHolder) viewHolder;
        holder.textView.setText(mSections.get(sectionIndex).month);
    }

    @Override
    public int getNumberOfSections() {
        return (mSections == null) ? 0 : mSections.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return mSections.get(sectionIndex).mList.size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    /**
     * 对应一个月内的所有消息
     */
    private class Section {
        String month;
        ArrayList<String> mList = new ArrayList<>();
    }

    public class NewsItemViewHolder extends SectioningAdapter.ItemViewHolder {
        @Bind(R.id.title)
        TextView tvTitle;

        @Bind(R.id.time)
        TextView tvTime;

        @Bind(R.id.pictureTextDrawable)
        ImageView imageView;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class HeadHolder extends SectioningAdapter.HeaderViewHolder {
        @Bind(android.R.id.text1)
        TextView textView;

        public HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            textView.setBackgroundColor(Color.GREEN);
            textView.setTextColor(Color.WHITE);
        }

    }
}
