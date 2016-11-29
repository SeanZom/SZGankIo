package com.demo.zhuwx.szgankio.mainpage.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.zhuwx.szgankio.R;
import com.demo.zhuwx.szgankio.base.BaseViewHolder;
import com.demo.zhuwx.szgankio.data.TopicEntity;
import com.demo.zhuwx.szgankio.utils.ResUtil;
import com.demo.zhuwx.szgankio.widget.DotsIndicatorView;

import java.util.List;

import static com.demo.zhuwx.szgankio.utils.CheckUtil.checkNotNull;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class TopicListAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_TEXT_PIC = 1;

    private List<TopicEntity> mTopics;
    private Context mContext;

    public TopicListAdapter(Context context, List<TopicEntity> topics) {
        mContext = context;
        mTopics = topics;
    }

    public void replaceData(List<TopicEntity> topics) {
        checkNotNull(topics);
        mTopics = topics;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(
                viewType == TYPE_TEXT ?
                        R.layout.item_main_text:
                        R.layout.item_main_text_with_pic,
                parent,
                false
        );

        if (viewType == TYPE_TEXT) {
            return new TextVH(root);
        } else {
            return new PicVH(root);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        TopicEntity item = mTopics.get(position);
        if (getItemViewType(position) == TYPE_TEXT) {
            initTextItem((TextVH) holder, item);
        } else {
            initPicItem((PicVH) holder, item);
        }
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    @Override
    public int getItemViewType(int position) {
        TopicEntity te = mTopics.get(position);
        return te.getImages().size() == 0 ? TYPE_TEXT : TYPE_TEXT_PIC;
    }

    private void initTextItem(TextVH vh, TopicEntity item) {
        vh.tvTitle.setText(item.getDesc());
        vh.tvWho.setText(item.getWho());
        vh.tvDate.setText(item.getPublishedAt());
    }

    private void initPicItem(PicVH vh, TopicEntity item) {
        vh.viewPager.setOffscreenPageLimit(2);
        PicSimplePagerAdapter adapter = new PicSimplePagerAdapter(mContext, item.getImages());
        vh.viewPager.setAdapter(adapter);
        // display dot indicator for viewpager when has more than 1 images
        if (item.getImages().size() > 1) {
            DotsIndicatorView dotsIndicatorView = new DotsIndicatorView(mContext);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.BOTTOM;
            params.setMargins(0, 0, 0, ResUtil.dp2Px(mContext, 16f));
            vh.flRoot.addView(dotsIndicatorView, 1, params);
            dotsIndicatorView.setViewPager(vh.viewPager);
        } else {
            for (int i = 0; i < vh.flRoot.getChildCount(); i ++) {
                if (vh.flRoot.getChildAt(i) instanceof DotsIndicatorView) {
                    vh.flRoot.removeViewAt(i);
                    break;
                }
            }
        }

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) vh.rlTextField.getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
        vh.rlTextField.setLayoutParams(layoutParams);
        vh.rlTextField.setBackgroundColor(mContext.getResources().getColor(R.color.black_42));
        vh.tvTitle.setTextColor(mContext.getResources().getColor(R.color.white));
        vh.tvTitle.setSingleLine(true);
        vh.tvTitle.setEllipsize(TextUtils.TruncateAt.END);
        vh.tvTitle.setText(item.getDesc());
        vh.tvWho.setText(item.getWho());
        vh.tvDate.setText(item.getPublishedAt());
    }


    /**
     *  pure text item view holder
     */
    static class TextVH extends BaseViewHolder {

        TextView tvTitle;
        TextView tvWho;
        TextView tvDate;

        public TextVH(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_topic_title);
            tvWho = (TextView) itemView.findViewById(R.id.tv_who);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

    /**
     *  text with image view holder
     */
    static class PicVH extends BaseViewHolder {

        FrameLayout flRoot;
        ViewPager viewPager;
        RelativeLayout rlTextField;
        /*
         *   Text field
         */
        TextView tvTitle;
        TextView tvWho;
        TextView tvDate;

        public PicVH(View itemView) {
            super(itemView);
            flRoot = (FrameLayout) itemView.findViewById(R.id.fl_root);
            viewPager = (ViewPager) itemView.findViewById(R.id.view_pager);
            rlTextField = (RelativeLayout) itemView.findViewById(R.id.layout_text_field);

            tvTitle = (TextView) rlTextField.findViewById(R.id.tv_topic_title);
            tvWho = (TextView) rlTextField.findViewById(R.id.tv_who);
            tvDate = (TextView) rlTextField.findViewById(R.id.tv_date);
        }
    }
}
