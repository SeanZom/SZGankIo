package com.demo.zhuwx.szgankio.mainpage.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.demo.zhuwx.szgankio.R;
import com.demo.zhuwx.szgankio.utils.ResUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/25
 *         Description :
 */

public class PicSimplePagerAdapter extends PagerAdapter {

    private List<String> mPics;
    private int mWidth;
    private int mHeight;

    private final LayoutInflater mLayoutInflater;

    public PicSimplePagerAdapter(Context context, List<String> pics) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPics = pics;
    }


    @Override
    public int getCount() {
        return mPics.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.view_pager_imageview, container, false);

        if (mWidth < 1 || mHeight < 1) {
            mWidth = ResUtil.getScreenWidth(container.getContext());
            mHeight = ResUtil.dp2Px(container.getContext(), 188f);
        }

        ImageView iv = (ImageView) itemView.findViewById(R.id.item_iv);

        Glide.with(container.getContext())
                .load(getCompleteUrl(mPics.get(position)))
                .centerCrop()
                .placeholder(R.drawable.ic_image_black_48dp)
                .error(R.drawable.ic_broken_image_black_48dp)
                .into(iv);

        container.addView(itemView);


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeView((View) object);
            Glide.clear(((View) object).findViewById(R.id.item_iv));
            unbindDrawables((View) object);
            object = null;
        } catch (Exception e) {
            Logger.w(">>destroyItem: failed to destroy item and clear it's used resources");
        }
    }

    private String getCompleteUrl(String oriUrl) {
        return String.format("%s?imageView2/0/w/%d/h/%d", oriUrl, mWidth, mHeight);
    }

    protected void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i ++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }
}
