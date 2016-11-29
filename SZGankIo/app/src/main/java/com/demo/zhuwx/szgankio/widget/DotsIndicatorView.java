package com.demo.zhuwx.szgankio.widget;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.zhuwx.szgankio.R;
import com.demo.zhuwx.szgankio.utils.ResUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * <p>
 * Date : 2016/10/5
 *
 * @author Sean Chu
 */

public class DotsIndicatorView extends LinearLayout{

    private static final int DEFAULT_DOT_DIAM = 8; // dip
    private static final int DEFAULT_DOT_DIAM_SELECTED = 12; // dip
    private static final int DEFAULT_DOT_MARGIN = 5; // dip

    private ViewPager mViewPager;

    private List<ImageView> dots;
    private int dotCount;
    private int mPxDotDiamUnselect;
    private int mPxDotDiamSelected;
    private int mPxDotMargin;
    private int initCounter;

    public DotsIndicatorView(Context context) {
        this(context, null, 0);
    }

    public DotsIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotsIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setGravity(Gravity.CENTER);
        mPxDotDiamUnselect = ResUtil.dp2Px(getContext(), DEFAULT_DOT_DIAM);
        mPxDotDiamSelected = ResUtil.dp2Px(getContext(), DEFAULT_DOT_DIAM_SELECTED);
        mPxDotMargin = ResUtil.dp2Px(getContext(), DEFAULT_DOT_MARGIN);
    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null) {
            return;
        }
        mViewPager = viewPager;
        dotCount = mViewPager.getAdapter().getCount();
        checkSize();
    }


    public void addDots() {
        dots = new ArrayList<>();
        for (int i = 0; i < dotCount; i ++) {
            ImageView dot = new ImageView(getContext());
            dot.setImageResource(i == mViewPager.getCurrentItem() ? R.drawable.ic_dot_selected : R.drawable.ic_dot_unselected);
            dot.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(dot, getDotLayoutParam(i == mViewPager.getCurrentItem()));
            dots.add(dot);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }
        });
    }

    public void selectDot(int index) {
        for (int i = 0; i < dotCount; i++) {
            int resId = (i == index) ? (R.drawable.ic_dot_selected) : (R.drawable.ic_dot_unselected);
            dots.get(i).setImageResource(resId);
            dots.get(i).setLayoutParams(getDotLayoutParam(i == index));
        }
    }

    private LayoutParams getDotLayoutParam(boolean isSelected) {
        LayoutParams params = new LayoutParams(
                isSelected ? mPxDotDiamSelected : mPxDotDiamUnselect,
                isSelected ? mPxDotDiamSelected : mPxDotDiamUnselect
        );

        params.leftMargin = mPxDotMargin;
        params.rightMargin = mPxDotMargin;

        return params;
    }

    private void checkSize() {
        if (initCounter ++ > 0) {
            return;
        }

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int layoutWidth = size.x;
        int totalWidth = (dotCount - 1) * mPxDotDiamUnselect + mPxDotDiamSelected + dotCount * 2 * mPxDotMargin;
        if (totalWidth > layoutWidth) {
            int dotUnit = layoutWidth / dotCount;
            mPxDotDiamUnselect = dotUnit / 3 * 2;
            mPxDotDiamSelected = mPxDotDiamUnselect * 2;
            mPxDotMargin = (dotUnit - mPxDotDiamUnselect) / 2;
        }

        addDots();
    }

}
