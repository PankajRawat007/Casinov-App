package com.pankajrawat.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.pankajrawat.homescreen.UserReviewInner.EXTRA_DESC;
import static com.pankajrawat.homescreen.UserReviewInner.EXTRA_NAME;

public class UserReviewInnerr extends AppCompatActivity {
    ImageView star1,star2,star3,star4,star5,star6,star7,star8,star9,star10;
    int cnstar;
    String cnrate="",rate="";
    String casinoname="",cnname="",name="",link="";
    Button visit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review_innerr);
        Intent intent=getIntent();
        star1=findViewById(R.id.innerstar1);
        star2=findViewById(R.id.innerstar2);
        star3=findViewById(R.id.innerstar3);
        star4=findViewById(R.id.innerstar4);
        star5=findViewById(R.id.innerstar5);
        star6=findViewById(R.id.inneremptystar1);
        star7=findViewById(R.id.inneremptystar2);
        star8=findViewById(R.id.inneremptystar3);
        star9=findViewById(R.id.inneremptystar4);
        star10=findViewById(R.id.inneremptystar5);
        visit=findViewById(R.id.visitus);




        link=intent.getStringExtra("link");
        casinoname=intent.getStringExtra("cn");
       cnname=intent.getStringExtra("cncn");
        String name=intent.getStringExtra(EXTRA_NAME);
        String desc=intent.getStringExtra(EXTRA_DESC);
        String androiddesc=intent.getStringExtra("des");
        String cndesc=intent.getStringExtra("cndes");
        rate=intent.getStringExtra("rating");
        String stars=intent.getStringExtra("r");
        cnrate=intent.getStringExtra("cnr");

        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webview=new Intent(UserReviewInnerr.this,WebViewActivity.class);
                webview.putExtra("links",link);
                startActivity(webview);
            }
        });


        if(rate==null&&cnrate==null) {

            int S=Integer.parseInt(stars);
            if(S==1){
                star1.setVisibility(View.VISIBLE);
                star7.setVisibility(View.VISIBLE);
                star8.setVisibility(View.VISIBLE);
                star9.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);
            }
            else  if(S==2){
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star8.setVisibility(View.VISIBLE);
                star9.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);


            }
            else  if(S==3){
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star9.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);
            }
            else  if(S==4){
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);
            }
            else  {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star5.setVisibility(View.VISIBLE);
            }

        }
        else if(rate==null&&stars==null) {
             int cnstar=Integer.parseInt(cnrate);

            if (cnstar == 1) {
                star1.setVisibility(View.VISIBLE);
                star7.setVisibility(View.VISIBLE);
                star8.setVisibility(View.VISIBLE);
                star9.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);
            } else if (cnstar == 2) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star8.setVisibility(View.VISIBLE);
                star9.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);


            } else if (cnstar == 3) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star9.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);
            } else if (cnstar == 4) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);
            } else {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star5.setVisibility(View.VISIBLE);
            }


        }
        else
        {
            int star = Integer.parseInt(rate);
            if (star == 1) {
                star1.setVisibility(View.VISIBLE);
                star7.setVisibility(View.VISIBLE);
                star8.setVisibility(View.VISIBLE);
                star9.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);
            } else if (star == 2) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star8.setVisibility(View.VISIBLE);
                star9.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);


            } else if (star == 3) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star9.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);
            } else if (star == 4) {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star10.setVisibility(View.VISIBLE);
            } else {
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star5.setVisibility(View.VISIBLE);
            }



        }



        String uri=intent.getStringExtra("nk");
        String urii=intent.getStringExtra("i");
        String uriii=intent.getStringExtra("cni");

        if(uri==null&&uriii==null )
        {
            ImageView imageView=findViewById(R.id.imageview1);
            ImageView imageView1=findViewById(R.id.backgrounreviewinner);
            Glide.with(UserReviewInnerr.this).load(urii).into(imageView);
            Glide.with(UserReviewInnerr.this).load(urii).into(imageView1);
        }
        else if(uriii==null&&urii==null)
        {
            ImageView imageView=findViewById(R.id.imageview1);
            ImageView imageView1=findViewById(R.id.backgrounreviewinner);
            Glide.with(UserReviewInnerr.this).load(uri).into(imageView);
            Glide.with(UserReviewInnerr.this).load(uri).into(imageView1);
        }
        else{
            ImageView imageView=findViewById(R.id.imageview1);
            ImageView imageView1=findViewById(R.id.backgrounreviewinner);
            Glide.with(UserReviewInnerr.this).load(uriii).into(imageView);
            Glide.with(UserReviewInnerr.this).load(uriii).into(imageView1);

        }

    if (desc==null&&cndesc==null)
    {
        TextView textView1=findViewById(R.id.txt2);
        textView1.setText(androiddesc);
    }
    else if(cndesc==null&&androiddesc==null)
    {
        TextView textView1=findViewById(R.id.txt2);
        textView1.setText(desc);
    }
    else{
        TextView textView1=findViewById(R.id.txt2);
        textView1.setText(cndesc);

    }

        TextView textView=findViewById(R.id.txtView);

    if(casinoname==null&&cnname==null){
        textView.setText(name);
    }
    else if( name==null&&cnname==null){

        textView.setText(casinoname);}
    else{
        textView.setText(cnname);}




    }
}
