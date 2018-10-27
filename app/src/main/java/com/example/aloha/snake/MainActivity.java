package com.example.aloha.snake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        GridBean.screenHeightPixels=dm.heightPixels;
        GridBean.screenWidthPixels=dm.widthPixels;
        GameView gameView= new GameView(this);
        setContentView(gameView);
    }
}
