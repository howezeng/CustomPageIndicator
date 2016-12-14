package com.example.zenglihao.custompageindicator.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zenglihao on 16/12/13.
 */

public class PageIndicatorView extends View {
    private Paint paintSolid = new Paint(Paint.FILTER_BITMAP_FLAG);
    private Paint paintSolidSelect = new Paint(Paint.FILTER_BITMAP_FLAG);
    private int count = 1; //圆点数目
    private int roundRadius = 3; //圆点半径 单位:dp
    private int roundSpace = 15; //圆点间的间距
    private int widthView = 0;
    private int heightView = 0;
    private int roundX = 0;
    private ViewPager pager;
    private int selectPagePositon = 0;
    private Context c;
    private int paintSlidColor = android.R.color.white;
    private int paintSlidSelectColor = android.R.color.black;

    public PageIndicatorView(Context context) {
        this(context, null, 0);
    }

    public PageIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.c = context;
        initPaint();

    }

    private void initPaint() {
        paintSolid.setColor(getResources().getColor(paintSlidColor));
        paintSolidSelect.setColor(getResources().getColor(paintSlidSelectColor));
    }


    public void attachToViewPager(ViewPager viewPager) {
        this.pager = viewPager;
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectPagePositon = position;
                invalidate();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            widthView = widthMeasure;
        } else {
            widthView = DensityUtil.dip2px(c, roundRadius) * 2 * count + (count - 1) * DensityUtil.dip2px(c, roundSpace);
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            heightView = heightMeasure;
        } else {
            heightView = DensityUtil.dip2px(c, roundRadius) * 2;
        }
        setMeasuredDimension(widthView, heightView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //由于是自定义view,不是viewGroup,所以在OnLayout方法中不做任何处理
    }

    //设置圆点半径(dp)
    public void setRoundRadius(int roundRadius) {
        this.roundRadius = roundRadius;
    }

    //设置圆点间距(dp)
    public void setRoundSpace(int roundSpace) {
        this.roundSpace = roundSpace;
    }

    //设置圆点颜色,传入资源ID
    public void setPaintSlidColor(int paintSlidColor) {
        this.paintSlidColor = paintSlidColor;
    }

    //设置圆点选中颜色,传入资源ID
    public void setPaintSlidSelectColor(int paintSlidSelectColor) {
        this.paintSlidSelectColor = paintSlidSelectColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        roundX = 0;
        for (int i = 0; i < count; i++) {
            canvas.drawCircle(roundX + DensityUtil.dip2px(c, roundRadius), height / 2, DensityUtil.dip2px(c, roundRadius), i == selectPagePositon ? paintSolidSelect : paintSolid);
            roundX = roundX + DensityUtil.dip2px(c, roundSpace);
        }

    }

    public void setCount(int count) {
        this.count = count;
    }
}
