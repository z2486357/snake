package com.example.aloha.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class GameView extends View {
    private Paint paint = new Paint();

    private GridBean gridBean;
    private SnakeBean snakeBean;


    public GameView(Context context) {
        super(context);
        init();

    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        gridBean = new GridBean();//创建格子对象，画格子时候使用
        snakeBean = new SnakeBean();//创建一个蛇对象。这时候蛇对象是空的，我们需要初始化一个值

        PointBean pointBean = new PointBean(gridBean.getGridSize()/2,gridBean.getGridSize()/2);
        snakeBean.getSnake().add(pointBean);//定义一个中心点 ，添加到蛇身上
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (gridBean != null) {
            paint.setColor(Color.RED);
            drawGrid(canvas);
        }
        if (snakeBean != null) {
            paint.setColor(Color.GREEN);
            drawSnake(canvas);
        }
    }

    private void drawSnake(Canvas canvas) {
        List<PointBean> snake = snakeBean.getSnake();
        for (PointBean point : snake) {
            int startX = gridBean.getOffset() + gridBean.getGridWidth() * point.getX();
            int stopX = startX + gridBean.getGridWidth();
            int startY = gridBean.getOffset() + gridBean.getGridWidth() * point.getY();
            int stopY = startY + +gridBean.getGridWidth();
            canvas.drawRect(startX, startY, stopX, stopY, paint);
        }
    }

    private void drawGrid(Canvas canvas) {
        //画竖线
        for (int i = 0; i <= gridBean.getGridSize(); i++) {
            int startX = gridBean.getOffset() + gridBean.getGridWidth() * i;
            int stopX = startX;
            int startY = gridBean.getOffset();//+gridBean.getGridWidth() * i
            int stopY = startY + gridBean.getLineLength();//
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
        //画横线
        for (int i = 0; i <= gridBean.getGridSize(); i++) {
            int startX = gridBean.getOffset();//+gridBean.getGridWidth() * i
            int stopX = startX + gridBean.getLineLength();

            int startY = gridBean.getOffset() + gridBean.getGridWidth() * i;
            int stopY = startY;
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
    }


}