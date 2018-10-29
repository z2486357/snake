package com.example.aloha.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


import java.util.List;


public class GameView extends View {
    private boolean isFailed = false;
    private boolean eatFood = false;
    private Paint paint = new Paint();

    private GridBean gridBean;
    private SnakeBean snakeBean;
    private PointBean food;

    private Control control = Control.UP;
    public GameView(Context context) {
        super(context);
        init();

    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        gridBean = new GridBean();
        snakeBean = new SnakeBean();
        food= new PointBean((int)(Math.random()*gridBean.getGridSize()),(int)(Math.random()*gridBean.getGridSize()));
        PointBean pointBean = new PointBean(gridBean.getGridSize()/2,gridBean.getGridSize()/2);
        snakeBean.getSnake().add(pointBean);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isFailed){
            return;
        }
        if (gridBean != null) {
            paint.setColor(Color.BLACK);
            drawGrid(canvas);
        }
        if (snakeBean != null) {
            paint.setColor(Color.GREEN);
            drawSnake(canvas);
        }

        if (food != null){
            paint.setColor(Color.RED);
            drawFood(canvas);
        }
    }
    private void drawFood(Canvas canvas) {
        int startX = gridBean.getOffset() + gridBean.getGridWidth() * food.getX();
        int stopX = startX + gridBean.getGridWidth();
        int startY = gridBean.getOffset() + gridBean.getGridWidth() * food.getY();
        int stopY = startY + +gridBean.getGridWidth();
        canvas.drawRect(startX, startY, stopX, stopY, paint);
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

        for (int i = 0; i <= gridBean.getGridSize(); i++) {
            int startX = gridBean.getOffset() + gridBean.getGridWidth() * i;
            int stopX = startX;
            int startY = gridBean.getOffset();//+gridBean.getGridWidth() * i
            int stopY = startY + gridBean.getLineLength();//
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }

        for (int i = 0; i <= gridBean.getGridSize(); i++) {
            int startX = gridBean.getOffset();//+gridBean.getGridWidth() * i
            int stopX = startX + gridBean.getLineLength();

            int startY = gridBean.getOffset() + gridBean.getGridWidth() * i;
            int stopY = startY;
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
    }

    public boolean refreshView(){

        List<PointBean> pointList = snakeBean.getSnake();
        PointBean point = pointList.get(0);
        PointBean pointNew = null;
        if (control == Control.LEFT) {
            pointNew = new PointBean(point.getX() - 1, point.getY());
        } else if (control == Control.RIGHT) {
            pointNew = new PointBean(point.getX() + 1, point.getY());
        } else if (control == Control.UP) {
            pointNew = new PointBean(point.getX(), point.getY() - 1);
        } else if (control == Control.DOWN) {
            pointNew = new PointBean(point.getX(), point.getY() + 1);
        }
        if (pointNew != null) {
            pointList.add(0, pointNew);
        }
        if (pointNew.getX()==food.getX() && pointNew.getY()==food.getY()){
            food= new PointBean((int)(Math.random()*gridBean.getGridSize()),(int)(Math.random()*gridBean.getGridSize()));
        }else {
            pointList.remove(pointList.get(pointList.size() - 1));
        }
        if(isFailed(point)){
            isFailed =true;
            invalidate();
            return true;
        }
        invalidate();
        return false;
    }
    private boolean isFailed( PointBean point){
        if (point.getY() == 0 && control == Control.UP ) {
            return true;
        } else if ( point.getX()  == 0 && control == Control.LEFT) {
            return true;
        } else if (point.getY() == gridBean.getGridSize() - 1 && control == Control.DOWN  ) {
            return true;
        } else if (point.getX() == gridBean.getGridSize() - 1 && control == Control.RIGHT ) {
            return true;
        }
        return false;
    }

    int x;
    int y;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction()  & MotionEvent.ACTION_MASK;
        if (action == KeyEvent.ACTION_DOWN) {
            x = (int) (event.getX());
            y = (int) (event.getY());
        }
        if (action== KeyEvent.ACTION_UP) {
            int x = (int) (event.getX());
            int y = (int) (event.getY());
            Control control  = null ;
            if (Math.abs(x - this.x) > Math.abs(y - this.y)) {
                if (x > this.x) {
                    control = Control.RIGHT;
                }
                if (x < this.x) {
                    control = Control.LEFT;
                }
            }else{
                if (y < this.y) {
                    control = Control.UP;
                }
                if (y > this.y) {
                    control = Control.DOWN;
                }
            }

            if (this.control == Control.UP || this.control == Control.DOWN) {
                if(control==Control.LEFT ||control==Control.RIGHT ){
                    this.control = control;
                }
            } else if (this.control == Control.LEFT || this.control == Control.RIGHT) {
                if(control==Control.UP||control==Control.DOWN ){
                    this.control = control;
                }
            }
        }
        return super.onTouchEvent(event);
    }
}