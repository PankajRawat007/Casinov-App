package com.pankajrawat.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactUs extends AppCompatActivity {
    private EditText username,email,phone,subject,message;
    private String susername,semail,sphone,ssubject,smessage;
    Button sendmessage;
    NavigationView navigationView;
    ImageView contactbar;

    String errors;
    private ProgressBar progressBar1;
    Circle circle=new Circle();
    public static final String URL="https://residentiary-deposi.000webhostapp.com/Home/contact_us";
    TextView home,reviews,bonuses,contactus,logout,usrname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        progressBar1=findViewById(R.id.spin_kit4);
        progressBar1.setVisibility(View.GONE);
        username=findViewById(R.id.conusernameedtText);
        email=findViewById(R.id.conemailedtText);
        phone=findViewById(R.id.conphoneeedtText);
        subject=findViewById(R.id.consubjectedtText);
        message=findViewById(R.id.conmessageedtText);

        navigationView=findViewById(R.id.contact_navigation_view);


        View headerView = navigationView.getHeaderView(0);

        contactbar=findViewById(R.id.contactbar);
        home=headerView.findViewById(R.id.navdrawerhome);
        reviews=headerView.findViewById(R.id.navdrawerreview);
        usrname=headerView.findViewById(R.id.user_name_display);
        bonuses=headerView.findViewById(R.id.navdrawerbonuses);
//        logout=headerView.findViewById(R.id.logoutcontact);
        contactus=headerView.findViewById(R.id.navdrawercontactus);

        String name=SessionManager.getInstance(ContactUs.this).getUserData().getName();
        usrname.setText(name);

        sendmessage=findViewById(R.id.sendmessage);

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                progressBar1.setIndeterminateDrawable(circle);
            }
        });

        contactbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);

                drawer.openDrawer(GravityCompat.START);


            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home=new Intent(ContactUs.this,ScrollingActivity.class);
                startActivity(home);
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent review=new Intent(ContactUs.this,UserReviewInner.class);
                startActivity(review);
            }
        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SessionManager.getInstance(ContactUs.this).logout();
//                Intent logout=new Intent(ContactUs.this,LoginActivity.class);
//                startActivity(logout);
//                finish();
//
//            }
//        });
        bonuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bonuses=new Intent(ContactUs.this,BonusActivity.class);
                startActivity(bonuses);
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ContactUs.this, "Already in Contact Us", Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void validate() {
        semail = email.getText().toString();
        susername = username.getText().toString();
        ssubject=subject.getText().toString();
        sphone = phone.getText().toString();
        smessage = message.getText().toString();

        if(semail == ""||semail.length()==0){
            email.setError("Empty Email field");
        }
        else if ( susername==""||susername.length()==0) {
            username.setError("Empty Username field");
        }
        else if(sphone==""||sphone.length()==0){
            phone.setError("Empty Phone Number field");

        }
        else if(smessage==""||smessage.length()==0){
            message.setError("Empty Message field");

        }
        else if(ssubject==""||ssubject.length()==0)
        {
            subject.setError("Empty Subject Field");
        }

        else {

            apiCall3();
        }
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
    private void apiCall3(){

        progressBar1.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar1.setVisibility(View.GONE);
                        Log.d("resdddp","responces "+response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String s=jsonObject.getString("result");
                            errors=jsonObject.getString("data");

                            if(s.equals("1")){
                                Toast.makeText(ContactUs.this, "Thanks for Your Feedback.", Toast.LENGTH_SHORT).show();
                                Intent home=new Intent(ContactUs.this,ScrollingActivity.class);
                                startActivity(home);
                                finish();


                            }
                            else
                            {
                                Toast.makeText(ContactUs.this, errors, Toast.LENGTH_SHORT).show();
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
                        progressBar1.setVisibility(View.GONE);

                    }


                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("username",susername);
                params.put("email",semail);
                params.put("phone",sphone);
                params.put("subject",ssubject);
                params.put("message",smessage);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ContactUs.this);
        requestQueue.add(stringRequest);
    }




}
