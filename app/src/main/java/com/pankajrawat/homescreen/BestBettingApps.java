package com.pankajrawat.homescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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

public class BestBettingApps extends AppCompatActivity implements BestBettingAppsAdapter.OnItemclickListener {
    RecyclerView androidrecyclerView;
    ArrayList<BestBettingAppsModel> androidreviewlist;
    public BestBettingAppsAdapter androidreviewAdapter;
    Circle cubeGrid=new Circle();
    String Rate,Description,CasinoName,ImageUrl;
    private static final String URL="https://residentiary-deposi.000webhostapp.com/Home/display_reviews";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_betting_apps);



    androidrecyclerView=findViewById(R.id.bestbettingrecyclerView);
    androidreviewlist=new ArrayList<>();
    progressBar=findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminateDrawable(cubeGrid);

        progressBar.setVisibility(View.VISIBLE);


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

                                if(category.equals(" BEST BETTING APPS")) {
                                    String Price = data.getString("price");
                                    ImageUrl = data.getString("image");
                                    String Discount = data.getString("discount");
                                    CasinoName=data.getString("company_name");
                                    Description=data.getString("description");
                                    Rate=data.getString("rating");
                                    String link=data.getString("link");
                                    androidreviewlist.add(new BestBettingAppsModel(ImageUrl, Price, Discount,Rate,CasinoName,Description,link));
                                }

                            }
                            LinearLayoutManager manager1=new LinearLayoutManager(BestBettingApps.this);
                            RecyclerView.LayoutManager rvlayoutmanager1 = manager1;

                            androidrecyclerView.setLayoutManager(rvlayoutmanager1);
                            androidreviewAdapter=new BestBettingAppsAdapter(BestBettingApps.this,androidreviewlist);
                            androidrecyclerView.setAdapter(androidreviewAdapter);
                            androidreviewAdapter.setOnItemClickListener(BestBettingApps.this);


                        }
                        else
                        {
                            Toast.makeText(BestBettingApps.this,"Backend Error", Toast.LENGTH_SHORT).show();

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

    RequestQueue requestQueue = Volley.newRequestQueue(BestBettingApps.this);
        requestQueue.add(stringRequest);


}


    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(BestBettingApps.this,UserReviewInnerr.class);
        BestBettingAppsModel clickedItem=androidreviewlist.get(position);
        intent.putExtra("cncn",clickedItem.getCasinoname());
        intent.putExtra("cndes",clickedItem.getDescription());
        intent.putExtra("cnr",clickedItem.getRate());
        intent.putExtra("cni",clickedItem.getImage());
        intent.putExtra("link",clickedItem.getLink());
        startActivity(intent);
    }

}
