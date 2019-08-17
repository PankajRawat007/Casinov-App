package com.pankajrawat.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.style.Circle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserReviewInner extends AppCompatActivity implements ReviewAdapter.OnItemclickListener{
    public static final String EXTRA_NAME="name",EXTRA_DESC="desc";
    public static final int EXTRA_IMAGE=1;
    NavigationView navigationView;
    TextView home,reviews,bonuses,contactus,logout,username;
    RecyclerView recyclerView;
    ArrayList<ModelReview> reviewlist;
    public ReviewAdapter reviewAdapter;
    private String errors;
    Circle cubeGrid=new Circle();
   ImageView bar1;
    private static final String URL="https://residentiary-deposi.000webhostapp.com/Home/display_reviews";


    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review_inner);
        recyclerView=findViewById(R.id.recyclerView);
        reviewlist=new ArrayList<>();
        progressBar=findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminateDrawable(cubeGrid);
        progressBar.setVisibility(View.VISIBLE);
        navigationView=findViewById(R.id.review_navigation_view);

        View headerView = navigationView.getHeaderView(0);
         bar1=findViewById(R.id.reviewbar);

        home=headerView.findViewById(R.id.navdrawerhome);
        reviews=headerView.findViewById(R.id.navdrawerreview);
        username=headerView.findViewById(R.id.user_name_display);
        bonuses=headerView.findViewById(R.id.navdrawerbonuses);
//        logout=headerView.findViewById(R.id.logoutuser);
        contactus=headerView.findViewById(R.id.navdrawercontactus);

        String name=SessionManager.getInstance(UserReviewInner.this).getUserData().getName();
        username.setText(name);

        bar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);

                drawer.openDrawer(GravityCompat.START);


            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home=new Intent(UserReviewInner.this,ScrollingActivity.class);
                startActivity(home);
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserReviewInner.this, "Already in Review", Toast.LENGTH_SHORT).show();
            }
        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SessionManager.getInstance(UserReviewInner.this).logout();
//                Intent logout=new Intent(UserReviewInner.this,LoginActivity.class);
//                startActivity(logout);
//                finish();
//
//            }
//        });
        bonuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bonuses=new Intent(UserReviewInner.this,BonusActivity.class);
                startActivity(bonuses);
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactus=new Intent(UserReviewInner.this,ContactUs.class);
                startActivity(contactus);
            }
        });





        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       progressBar.setVisibility(View.GONE);

                        Log.d("resdddp","responces "+response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String s=jsonObject.getString("result");

                            JSONArray array=jsonObject.getJSONArray("data");

                            if(s.equals("1")){


                              for(int i=0;i<array.length();i++)
                              {
                                  JSONObject data=array.getJSONObject(i);
                                  String userid=data.getString("id");
                                  String Title=data.getString("company_name");
                                  String ImageUrl=data.getString("image");
                                  String desc=data.getString("description");
                                  String  rating=data.getString("rating");
                                  String link=data.getString("link");

                                  reviewlist.add(new ModelReview(ImageUrl,Title,rating,desc,link));

                              }
                                LinearLayoutManager manager=new LinearLayoutManager(UserReviewInner.this);
                              RecyclerView.LayoutManager rvlayoutmanager = manager;

                                recyclerView.setLayoutManager(rvlayoutmanager);
                                reviewAdapter=new ReviewAdapter(UserReviewInner.this,reviewlist);
                                recyclerView.setAdapter(reviewAdapter);
                                reviewAdapter.setOnItemClickListener(UserReviewInner.this);


                            }
                            else
                            {
                                Toast.makeText(UserReviewInner.this,"Backend Error", Toast.LENGTH_SHORT).show();

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


                    }


                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();



                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(UserReviewInner.this);
        requestQueue.add(stringRequest);

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

    @Override
    public void onItemClick(int position) {
        Intent detailIntent=new Intent(UserReviewInner.this,UserReviewInnerr.class);
        ModelReview clickedItem=reviewlist.get(position);
        detailIntent.putExtra("nk",clickedItem.getImage());
        detailIntent.putExtra(EXTRA_NAME,clickedItem.getName());
        detailIntent.putExtra(EXTRA_DESC,clickedItem.getDescription());
        detailIntent.putExtra("rating",clickedItem.getStars());
        detailIntent.putExtra("link",clickedItem.getLink());
        startActivity(detailIntent);
    }

    }

