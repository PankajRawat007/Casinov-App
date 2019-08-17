package com.pankajrawat.homescreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.ybq.android.spinkit.style.Circle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.pankajrawat.homescreen.R.id.viewPager;

public class ScrollingActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView home,reviews,bonuses,contactus,logout,username;
    RequestQueue rq;
    ImageView bar;
    JSONArray array;
    int count;

    SessionManager sessionManager;
    private Timer timer;
    private int current_position=0;
    NavigationView navigationView;
    Button btn1,btn2,btn3,btn4,subsc;
    ProgressBar progressBar;

    ViewPager mPager;
    ViewPagerAdapter adapter ;
    List<SliderModel> sliderImg;
    Circle cubeGrid=new Circle();
    String request_url = "https://residentiary-deposi.000webhostapp.com/Home/get_image";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Developer  of this App is Pankaj Rawat", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        progressBar=findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminateDrawable(cubeGrid);
        subsc=findViewById(R.id.subscribe);
        subsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pop=new Intent(ScrollingActivity.this,PopUpActivity.class);
                startActivity(pop);
            }
        });

        progressBar.setVisibility(View.VISIBLE);

        rq = CustomVolleyRequest.getInstance(this).getRequestQueue();
        sliderImg = new ArrayList<>();
        mPager=findViewById(viewPager);
        sendRequest();



        btn1 =findViewById(R.id.button1);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(ScrollingActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);

                btn1.startAnimation(myAnim);

                Intent casino=new Intent(ScrollingActivity.this,CasinoMobileBonuses.class);
                startActivity(casino);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(ScrollingActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);

                btn2.startAnimation(myAnim);

                Intent ios=new Intent(ScrollingActivity.this,IosCasinoApps.class);
                startActivity(ios);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(ScrollingActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);

                btn3.startAnimation(myAnim);

                Intent bestbetting=new Intent(ScrollingActivity.this,BestBettingApps.class);
                startActivity(bestbetting);

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(ScrollingActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);

                btn4.startAnimation(myAnim);

                Intent android=new Intent(ScrollingActivity.this,AndroidCasinoApp.class);
                startActivity(android);
            }
        });





        navigationView=findViewById(R.id.design_navigation_view);


        View headerView = navigationView.getHeaderView(0);

        bar=findViewById(R.id.scrollingbar);
        home=headerView.findViewById(R.id.navdrawerhome);
        reviews=headerView.findViewById(R.id.navdrawerreview);
        username=headerView.findViewById(R.id.user_name_display);
        bonuses=headerView.findViewById(R.id.navdrawerbonuses);
        logout=headerView.findViewById(R.id.logout1);
        contactus=headerView.findViewById(R.id.navdrawercontactus);

        String name=SessionManager.getInstance(ScrollingActivity.this).getUserData().getName();
        username.setText(name);


        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);

                drawer.openDrawer(GravityCompat.START);


            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ScrollingActivity.this, "Already in Home", Toast.LENGTH_SHORT).show();
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent review=new Intent(ScrollingActivity.this,UserReviewInner.class);
                startActivity(review);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager.getInstance(ScrollingActivity.this).logout();
                Intent logout=new Intent(ScrollingActivity.this,LoginActivity.class);
                startActivity(logout);
                finish();

            }
        });
        bonuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bonuses=new Intent(ScrollingActivity.this,BonusActivity.class);
                startActivity(bonuses);
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactus=new Intent(ScrollingActivity.this,ContactUs.class);
                startActivity(contactus);
            }
        });




        try {

            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ScrollerSpeed scroller = new ScrollerSpeed(mPager.getContext(), new DecelerateInterpolator());
            mScroller.set(mPager, scroller);
        }catch (NoSuchFieldException e){} catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if(current_position==count){
                    current_position=0;
                    mPager.setCurrentItem(current_position++,false);
                }
                else{
                    if (current_position!=mPager.getCurrentItem()+1){
                        current_position = mPager.getCurrentItem()+1;
                    }
                    mPager.setCurrentItem(current_position++,true);}

            }

        };

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },400,4000);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void sendRequest(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,request_url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String s = jsonObject.getString("result");

                            array = jsonObject.getJSONArray("data");
                            count=array.length();

                            if (s.equals("1")) {
                                for (int i = 0; i < array.length(); i++) {
//
                                    SliderModel sliderUtils = new SliderModel();
                                    JSONObject data = array.getJSONObject(i);

                                    sliderUtils.setSliderImageUrl(data.getString("image_name"));
                                    sliderImg.add(sliderUtils);

                                }
                                adapter = new ViewPagerAdapter(sliderImg, ScrollingActivity.this);

                                mPager.setAdapter(adapter);
                            } else {
                                Toast.makeText(ScrollingActivity.this, "Backend Error", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);

                    }


                });
        CustomVolleyRequest.getInstance(this).addToRequestQueue(stringRequest);


    }
}
