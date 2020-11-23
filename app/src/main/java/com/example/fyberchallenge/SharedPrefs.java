package com.example.fyberchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    Context mcontext;
    SharedPreferences pref;
    SharedPreferences.Editor  editor;

    public SharedPrefs(Context context) {
    this.mcontext = context;
    pref = mcontext.getSharedPreferences("UserCredentials", mcontext.MODE_PRIVATE);
    editor = pref.edit();
    }

    public String getSharedPref(String key){
        return pref.getString(key,null);
    }

    public void saveSharedPref(String key, String value){
    editor.putString(key,value);
    editor.commit();
    }

    public void deleteSharedPref(){
        editor.clear();
        editor.commit();
    }
}
