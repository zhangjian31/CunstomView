package com.example.zhangjian.cunstomview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangjian on 2017/5/30.
 */

public class MyLL extends ViewGroup {
    private int screenWidth;
    private int perCount = 5;
    private float density;
    private int div = 10;

    public MyLL(Context context) {
        this(context, null, 0);
    }

    public MyLL(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLL(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        density = getResources().getDisplayMetrics().density;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int width_size = MeasureSpec.getSize(widthMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        int height_size = MeasureSpec.getSize(heightMeasureSpec);


        int count = getChildCount();
        int size;
        if (count <= 3) {
            size = (int) ((screenWidth - density * div * (count + 1)) / count);
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                int measureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
                measureChild(child, measureSpec, heightMeasureSpec);
            }

        } else {
            size = (int) ((screenWidth - density * div * (3 + 1)) / (float) (3 + 0.5));
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                int measureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
                measureChild(child, measureSpec, heightMeasureSpec);
            }
        }
        Log.e("#########","setMeasuredDimension-----1");
        setMeasuredDimension((int) (count * size + density * div * (count + 1)), heightMeasureSpec);
        Log.e("#########","setMeasuredDimension-----2");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("#########onSizeChanged:","w:"+w+"    "+"h:"+h+"    "+"oldw:"+oldw+"    "+"oldh:"+oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int left;
        Log.e("#########","onLayout-----1");
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            left = (int) (density * div * (i + 1) + child.getMeasuredWidth() * i);
            child.layout(left, t, left + child.getMeasuredWidth()+10, b);
        }
        Log.e("#########","onLayout-----2");
    }
}
