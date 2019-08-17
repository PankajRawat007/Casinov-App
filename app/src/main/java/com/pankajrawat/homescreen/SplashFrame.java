package com.pankajrawat.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashFrame extends AppCompatActivity {
    ImageView imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_frame);
        String sVAL=SessionManager.getInstance(SplashFrame.this).isLogin();
        if(sVAL.equals("true")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashFrame.this, ScrollingActivity.class));
                    finish();
                }
            },2000);
        }
        else
        {
            Thread timer =new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(4000);
                        Intent intent=new Intent(getApplicationContext(),AfterSplash.class);
                        startActivity(intent);
                        finish();
                        super.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.start();
        }

        imageView8=findViewById(R.id.fadeimage);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        imageView8.startAnimation(animation);


    }

}

