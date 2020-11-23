package com.example.fyberchallenge.service;

import android.util.Log;

import androidx.annotation.VisibleForTesting;

import com.example.fyberchallenge.api.ApiInterface;
import com.example.fyberchallenge.api.ApiService;
import com.example.fyberchallenge.model.Offers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@VisibleForTesting
public class OfferServiceImpl implements IOfferService {

    @Override
    public void getOffers(OfferAPICallback offerAPICallback) {
        ApiInterface apiInterface = ApiService.createService(ApiInterface.class);
        Call<Offers> call = apiInterface.getOffers();
        call.enqueue(new Callback<Offers>() {

            @Override
            public void onResponse(Call<Offers> call, Response<Offers> response) {
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    Offers offers = response.body();
                    offerAPICallback.onSuccess(offers.getOffers());
                } else {
                    offerAPICallback.onError(new Exception("Response code is not successfull: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Offers> call, Throwable t) {
                Log.e("TAG", t.getMessage());
                offerAPICallback.onError(new Exception(t.getMessage()));
            }
        });
    }
}
