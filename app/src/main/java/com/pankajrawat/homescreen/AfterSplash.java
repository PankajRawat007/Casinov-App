package com.pankajrawat.homescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AfterSplash extends AppCompatActivity {
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash);
        btn1=findViewById(R.id.signupdirect);
        btn2=findViewById(R.id.logindirect);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign=new Intent(AfterSplash.this,SignUp.class);
                startActivity(sign);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log=new Intent(AfterSplash.this,LoginActivity.class);
                startActivity(log);
                finish();
            }
        });
    }
}
