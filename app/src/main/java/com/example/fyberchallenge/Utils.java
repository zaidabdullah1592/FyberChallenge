package com.example.fyberchallenge;

import androidx.annotation.NonNull;

public class Utils {
    public static final String app_id = "2070";
    public static final String user_id_name = "superman";
    public static final String token = "1c915e3b5d42d05136185030892fbb846c278927";

    @NonNull
    public static final Long timeStamp() {
        Long current_time = System.currentTimeMillis();
        return current_time;
    }
}
