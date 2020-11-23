package com.example.fyberchallenge.api;

import com.example.fyberchallenge.model.Offers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("offers.json?appid=2070&apple_idfa_tracking_enabled=false&google_ad_id_limited_tracking_enabled=false&ip=109.235.143.113&locale=de&offer_types=112&phone_version=Unknown&timestamp=1606053761&uid=superman&hashkey=c5202242085093fc16db385754ac5af33b00e156")
    Call<Offers> getOffers();
}
//
//    @GET("offers.json?appid={appid}&apple_idfa_tracking_enabled=false&google_ad_id_limited_tracking_enabled=false&ip=109.235.143.113&locale=de&offer_types=112&phone_version=Unknown&timestamp=1606053761&uid={uid}&hashkey=c5202242085093fc16db385754ac5af33b00e156")
//    Call<Offers> getOffers(
//            @Path("appid") String appid,
//            @Path("uid") String uid,
//            @Path("hashkey") String hashkey
//    );