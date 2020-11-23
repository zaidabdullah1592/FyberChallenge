package com.example.fyberchallenge.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fyberchallenge.R;
import com.example.fyberchallenge.SharedPrefs;

public class MainActivity extends AppCompatActivity {

    SharedPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = new SharedPrefs(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        prefs.deleteSharedPref();
        super.onDestroy();
    }
}