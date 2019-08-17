package com.pankajrawat.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ForgetPassword extends AppCompatActivity {
    EditText email, password, cpass;
    String semail, sname, spass, scnfpass;
    TextView logintxt;
    ProgressBar progressBar1;
    Circle cubeGrid = new Circle();
    String errors;
    private Button forgetbtn;
    public static final String URL = "https://residentiary-deposi.000webhostapp.com/Home/forgot_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        progressBar1 = findViewById(R.id.spin_kit1);
        progressBar1.setVisibility(View.GONE);

        forgetbtn = findViewById(R.id.forgetsubmit);

        email = findViewById(R.id.forgetemailedtText);

        password = findViewById(R.id.forgetpassedtText);
        cpass = findViewById(R.id.forgetconfirmpassedtText);


        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                progressBar1.setIndeterminateDrawable(cubeGrid);

            }
        });
    }

    private void validate() {
        semail = email.getText().toString();


        spass = password.getText().toString();
        scnfpass = cpass.getText().toString();

        if (semail == "" || semail.length() == 0) {
            email.setError("Empty Email");
        } else if (!semail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            email.setError("Enter Valid Email");

        } else if (spass == "" || spass.length() == 0) {
            password.setError("Empty Password");

        } else if (spass.length() < 6) {
            password.setError("Minimum Password length is 6");

        } else if (scnfpass == "" || scnfpass.length() == 0) {
            cpass.setError("Empty Confirm Password");

        } else if (!spass.equals(scnfpass)) {
            cpass.setError("Enter Similar Password");
        } else {

            apiCall97();
        }

    }
    private void apiCall97(){

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
                                Intent intent=new Intent(ForgetPassword.this,LoginActivity.class);
                                Toast.makeText(ForgetPassword.this, "Password Updated Succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();


                            }
                            else
                            {
                                Toast.makeText(ForgetPassword.this, errors, Toast.LENGTH_SHORT).show();
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


                params.put("email",semail);
                params.put("password",spass);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ForgetPassword.this);
        requestQueue.add(stringRequest);
    }


}
