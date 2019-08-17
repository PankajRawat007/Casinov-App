package com.pankajrawat.homescreen;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class BonusActivity extends AppCompatActivity implements BonusAdapter.OnItemclickListener {
    RecyclerView androidrecyclerView;
    ArrayList<BonusModel> androidreviewlist;
    public BonusAdapter androidreviewAdapter;
    Circle cubeGrid=new Circle();
    NavigationView navigationView;
    ImageView bonusbar;
    String Rate,Description,CasinoName,ImageUrl;
    TextView home,reviews,bonuses,contactus,logout,username;
    private static final String URL="https://residentiary-deposi.000webhostapp.com/Home/display_reviews";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);


        androidrecyclerView=findViewById(R.id.recyclerView);
        androidreviewlist=new ArrayList<>();
        progressBar=findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminateDrawable(cubeGrid);

        progressBar.setVisibility(View.VISIBLE);

        navigationView=findViewById(R.id.bonus_navigation_view);


        View headerView = navigationView.getHeaderView(0);

        bonusbar=findViewById(R.id.bonusbar);
        home=headerView.findViewById(R.id.navdrawerhome);
        reviews=headerView.findViewById(R.id.navdrawerreview);
        username=headerView.findViewById(R.id.user_name_display);
        bonuses=headerView.findViewById(R.id.navdrawerbonuses);
//        logout=headerView.findViewById(R.id.logoutbonus);
        contactus=headerView.findViewById(R.id.navdrawercontactus);
        String name=SessionManager.getInstance(BonusActivity.this).getUserData().getName();
        username.setText(name);


        bonusbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);

                drawer.openDrawer(GravityCompat.START);


            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home=new Intent(BonusActivity.this,ScrollingActivity.class);
                startActivity(home);
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent review=new Intent(BonusActivity.this,UserReviewInner.class);
                startActivity(review);
            }
        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SessionManager.getInstance(BonusActivity.this).logout();
//                Intent logout=new Intent(BonusActivity.this,LoginActivity.class);
//                startActivity(logout);
//                finish();
//
//            }
//        });
        bonuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BonusActivity.this, "Already in Bonuses", Toast.LENGTH_SHORT).show();
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactus=new Intent(BonusActivity.this,ContactUs.class);
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

                                       String category=data.getString("category_name");


                                        String Price = data.getString("price");
                                        ImageUrl = data.getString("image");
                                        String Discount = data.getString("discount");
                                        CasinoName=data.getString("company_name");
                                        Description=data.getString("description");
                                        Rate=data.getString("rating");
                                        String link=data.getString("link");

                                        androidreviewlist.add(new BonusModel(ImageUrl, Price, Discount,Rate,CasinoName,Description,link));


                                }

                                LinearLayoutManager manager1=new LinearLayoutManager(BonusActivity.this);
                                RecyclerView.LayoutManager rvlayoutmanager1 = manager1;

                                androidrecyclerView.setLayoutManager(rvlayoutmanager1);
                                androidreviewAdapter=new BonusAdapter(BonusActivity.this,androidreviewlist);
                                androidrecyclerView.setAdapter(androidreviewAdapter);
                                androidreviewAdapter.setOnItemClickListener( BonusActivity.this);


                            }
                            else
                            {
                                Toast.makeText(BonusActivity.this,"Backend Error", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
//                            checkUploadVideoOrImage--
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);

                    }


                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();



                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(BonusActivity.this);
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
        Intent intent=new Intent(BonusActivity.this,UserReviewInnerr.class);
        BonusModel clickedItem=androidreviewlist.get(position);
        intent.putExtra("cncn",clickedItem.getCasinoname());
        intent.putExtra("cndes",clickedItem.getDescription());
        intent.putExtra("cnr",clickedItem.getRate());
        intent.putExtra("cni",clickedItem.getImage());
        intent.putExtra("link",clickedItem.getLink());
        startActivity(intent);
    }

}

