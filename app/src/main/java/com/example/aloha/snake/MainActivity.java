package com.example.aloha.snake;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final int WHAT_REFRESH = 200;
    private int refreshTime = 200;
    private int time=0;
    private Paint paint = new Paint();
    private GameView gameView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(WHAT_REFRESH == msg.what){
                time++;
                if (time%5==0) {
                    if (refreshTime>50){
                        refreshTime--;
                    }
                }
                boolean isFailed = gameView.refreshView();
                if(!isFailed){
                    sendControlMessage();
                }else{
                    setContentView(R.layout.activity_main);
                    TextView textView=(TextView)findViewById(R.id.textView);
                    textView.setVisibility(View.VISIBLE);
                    Button button=(Button)findViewById(R.id.button);
                    button.setText("Restart");
                }

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    private void sendControlMessage(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(WHAT_REFRESH);
            }
        },refreshTime);
    }

    public void ButtonOnclick(View view) {
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        GridBean.screenHeightPixels=dm.heightPixels;
        GridBean.screenWidthPixels=dm.widthPixels;
        gameView = new GameView(this);
        gameView.setClickable(true);
        setContentView(gameView);
        sendControlMessage();
    }
}