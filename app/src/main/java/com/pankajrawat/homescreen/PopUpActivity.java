package com.pankajrawat.homescreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PopUpActivity extends AppCompatActivity {
    private EditText email;
    Button done;
    String semail;
    String request_url = "https://residentiary-deposi.000webhostapp.com/Home/subscribe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        email = findViewById(R.id.emailpop);

        done = findViewById(R.id.submitpop);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

done.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        semail = email.getText().toString();
        if(semail.equals(null)||semail.length()==0)
        {
            email.setError("Please Enter The Email");
        }
        else if(!semail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
            email.setError("Enter Valid Email");

        }
        else{

            apiCall8();
        }

    }
});
    }

    private void apiCall8() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, request_url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finish();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String s = jsonObject.getString("result");

                            if (s.equals("1")) {
                                Toast.makeText(PopUpActivity.this, "SUCCESFULLY SUBSCRIBED", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(PopUpActivity.this, "Backend Error", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
//
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                params.put("email", semail);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(PopUpActivity.this);
        requestQueue.add(stringRequest);
    }
}


