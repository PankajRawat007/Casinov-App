package com.pankajrawat.homescreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    TextView signupbtn,forget;
    ProgressBar progressBar;
    String semail,spass;
    SessionManager sessionManager;
    String errors;
    Circle cubeGrid=new Circle();

    private Button loginbtn;
    public static final String URL="https://residentiary-deposi.000webhostapp.com/home/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


forget=findViewById(R.id.forgettxtview);
forget.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent forgetpass=new Intent(LoginActivity.this,ForgetPassword.class);
        startActivity(forgetpass);
    }
});



    progressBar=findViewById(R.id.spin_kit);
        progressBar.setVisibility(View.GONE);

        String sVAL=SessionManager.getInstance(LoginActivity.this).isLogin();
        if(sVAL.equals("true")){
            Intent q=new Intent(LoginActivity.this,ScrollingActivity.class);
            startActivity(q);
            finish();
        }


        loginbtn=findViewById(R.id.login);
        signupbtn=findViewById(R.id.signuptxt);

        email=findViewById(R.id.loginemail);

        password=findViewById(R.id.loginpass);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intp=new Intent(LoginActivity.this,SignUp.class);
                startActivity(intp);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                progressBar.setIndeterminateDrawable(cubeGrid);

            }
        });
    }

    private void validate() {
        semail = email.getText().toString();


        spass = password.getText().toString();

        if(semail == ""||semail.length()==0){
            email.setError("Empty Email");
        }
        else if(!semail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
            email.setError("Enter Valid Email");

        }
        else if(spass==""||spass.length()==0){
            password.setError("Empty Password");

        }
        else if(spass.length()<6){
            password.setError("Minimum Password lenth is 6");

        }
        else {

            apiCall2();
        }
    }
    private void apiCall2(){

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
                            errors=jsonObject.getString("data");

                            if(s.equals("1")){
                                String email=jsonObject.getString("email").trim();
                                String id=jsonObject.getString("login").trim();
                                String username=jsonObject.getString("username").trim();
                                SessionManager.getInstance(LoginActivity.this).userLogin("true");
                                UserModel model=new UserModel(id,username,email);
                                SessionManager.getInstance(LoginActivity.this).saveUserData(model);
                                Intent q=new Intent(LoginActivity.this,LoginActivity.class);
                                startActivity(q);
                                finish();


                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, errors, Toast.LENGTH_SHORT).show();

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
                        progressBar.setVisibility(View.GONE);

                    }


                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                Log.d("param",s_cpass);
                Map<String, String> params = new HashMap<>();
//                    params.put("media_id", media_id);

                params.put("email",semail);
                params.put("password",spass);

//                params.put("gender",gen);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }
    public void hideKeyboard1(View view)
    {
        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0 );
    }

}

