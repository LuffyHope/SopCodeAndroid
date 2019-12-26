package com.example.sopcode.studyui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomizeView extends View {

    private Paint paint;
    private int heightsize;
    private int widthsize;
    private int width;
    private int hight;
    private int everyOneWidth;

    public CustomizeView(Context context) {
        super(context);
        init(context);
    }

    public CustomizeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomizeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomizeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setStrokeWidth(5f);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        widthsize = MeasureSpec.getSize(widthMeasureSpec);

        heightsize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        Log.d("==--==--==", "onMeasure: widthmode = " + widthmode + "\n widthsize = " + widthsize + "\n heightsize = " + heightsize + "\nheightMode = " + heightMode);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("==--==--==", "onSizeChanged: enter");
        width = w;
        hight = h;
        everyOneWidth = w / 5;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("==--==--==", "onLayout: enter");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        testSqura(canvas);
    }

    private void testSqura(Canvas canvas) {
        canvas.drawRect(0, 0, width, width, paint);
        Path path = new Path();
        paint.setStrokeWidth(0);
        for (int i = 1; i < 5; i++) {
            path.moveTo(0, everyOneWidth * i);
            path.lineTo(width, everyOneWidth * i);
            canvas.drawPath(path, paint);
        }

        for (int i = 0; i < 5; i++) {
            path.moveTo(everyOneWidth * i, 0);
            path.lineTo(everyOneWidth * i, width);
            canvas.drawPath(path, paint);
        }


        Path path1 = new Path();
        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.RED);
        paint1.setStrokeWidth(5);
        paint1.setStyle(Paint.Style.STROKE);
        path1.moveTo(0, width / 2);
        path1.lineTo(2 * everyOneWidth, everyOneWidth);
        path1.lineTo(2 * everyOneWidth, 3 * everyOneWidth);
        path1.lineTo(3 * everyOneWidth, 2 * everyOneWidth);
        path1.lineTo(width, 2 * everyOneWidth);
        canvas.drawPath(path1, paint1);
    }

    private void testPath(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF(100, 5, 800, 400);
        path.addArc(rectF, -90, 180);
        canvas.drawPath(path, paint);
    }

    private void drawClock(Canvas canvas) {
        canvas.translate(widthsize / 2, heightsize / 2);
        canvas.drawCircle(0, 0, 500, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 10, paint);
        int clockNumber = 10;
        for (int i = 0; i < 60; i++) {
            float distance;
            if (i % 5 == 0) {
                distance = 60;
                clockNumber++;
                Log.d("==============", "onDraw: clockNumber = " + clockNumber);
                Paint paint2 = new Paint();
                paint2.setStyle(Paint.Style.STROKE);
                paint2.setTextSize(40f);
                canvas.drawText((clockNumber % 12 + 1) + "", 5, -500 + distance + 40, paint2);
            } else {
                distance = 30;
            }
            canvas.drawLine(0, 500, 0, 500 - distance, paint);
            canvas.rotate(6, 0, 0);
        }
    }
}
