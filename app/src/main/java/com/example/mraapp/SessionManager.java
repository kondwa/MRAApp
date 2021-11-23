package com.example.mraapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static Context context;
    private static SharedPreferences prefs;
    private static int position;

    public static String getUsername() {
        return prefs.getString(USERNAME,null);
    }

    public static void setUsername(String username) {
        if(prefs == null){
            SessionManager.prefs = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USERNAME,username);
        editor.apply();
    }

    private static String USERNAME="username";
    private static String AUTH="auth";

    public SessionManager(Context context) {
        SessionManager.context = context;
        SessionManager.prefs = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
    }

    public static String getAuthorization() {
        return prefs.getString(AUTH,null);
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        SessionManager.context = context;
    }

    public static void setAuthorization(String authorization) {
        if(prefs == null){
            SessionManager.prefs = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(AUTH,authorization);
        editor.apply();
    }
}
