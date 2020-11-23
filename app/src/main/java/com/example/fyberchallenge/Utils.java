package com.example.fyberchallenge;

import android.content.Context;

import androidx.annotation.NonNull;

public class Utils {
    Context mContext;
    public static final String app_id = "2070";
    public static final String user_id_name = "superman";
    public static final String token = "1c915e3b5d42d05136185030892fbb846c278927";

    public Utils(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    public static final Long timeStamp() {
        Long current_time = System.currentTimeMillis();
        return current_time;
    }

    public static String gethashkey(){
        return "c5202242085093fc16db385754ac5af33b00e156";
    }
}
