package com.example.zhangjian.cunstomview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.zhangjian.cunstomview.R;

/**
 * Created by zhangjian on 2017/5/30.
 */

public class CircleView extends View {
    private Paint paint;
    private int width;
    private int height;
    private float scale = 1;
    private int radius = 200;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.e("######", "density:" + getResources().getDisplayMetrics().density);
        Log.e("######", "densityDpi:" + getResources().getDisplayMetrics().densityDpi);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CircleView, defStyleAttr, R.style.AppTheme);
        radius = a.getDimensionPixelSize(R.styleable.CircleView_radius, 50);
        Log.e("######", "radius:" + radius);
        a.recycle();
        paint = new Paint();
        init();
    }

    private void init() {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        setBackgroundColor(Color.GREEN);

    }

    private ValueAnimator animator;

    private void startAnimator() {
        animator = ValueAnimator.ofFloat(1.0f, 2.0f);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = (float) animation.getAnimatedValue();
                invalidate();
                Log.e("######currentThread",Thread.currentThread().getName());
            }

        });
        animator.setRepeatCount(-1);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimator();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                animator.cancel();
                animator.end();
            }
        },10000);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("######onDraw", "width:" + width + "height:" + height + "radius:" + radius);
        canvas.drawCircle(width / 2, height / 2, scale * radius, paint);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        Log.e("######", "width:" + width + "  height: " + height);
    }
}
