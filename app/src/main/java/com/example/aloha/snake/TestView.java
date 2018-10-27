package com.example.aloha.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

public class TestView extends View {
    public TestView (Context context){
        super(context);
    }
    public TestView (Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
    }
    //绘制方法
    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        canvas.drawLine(100,100,100,1000,mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(200,200,400,400,mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(500,500,200,mPaint);
    }
}
