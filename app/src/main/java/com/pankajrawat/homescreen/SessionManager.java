package com.pankajrawat.homescreen;




import android.content.Context;
import android.content.SharedPreferences;


public class SessionManager {

    private static final String SHARED_PREF_NAME = "com.example.notion";

    private static final String KEY_ID = "keyid";
    private static final String KEY_NAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String IS_LOGIN = "false";
    public static final String PROFILE_PIC = "url";

    private static SessionManager mInstance;
    private static Context mCtx;

    private SessionManager(Context context) {
        mCtx = context;
    }


    public static synchronized SessionManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SessionManager(context);
        }
        return mInstance;
    }



    public void userLogin(String s){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IS_LOGIN,s);
        editor.commit();
    }
    public String isLogin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(IS_LOGIN,"false");
    }

    public void saveUserData(UserModel userModel){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID,userModel.getId());
        editor.putString(KEY_NAME,userModel.getName());
        editor.putString(KEY_EMAIL,userModel.getMail());

        editor.commit();
    }
    public UserModel getUserData(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserModel(
                sharedPreferences.getString(KEY_ID,""),
                sharedPreferences.getString(KEY_NAME,""),sharedPreferences.getString(KEY_EMAIL,"")
        );
    }


    public void saveProfilePic(String s){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PROFILE_PIC,s);
        editor.commit();
    }
    public String getProfilePic(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PROFILE_PIC,"");
    }

    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
//      mCtx.startActivity(new Intent(mCtx, LoginSignupActivity.class));
    }

}


