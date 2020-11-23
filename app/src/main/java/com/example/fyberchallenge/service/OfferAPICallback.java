package com.example.fyberchallenge.service;

import com.example.fyberchallenge.model.Offer;

import java.util.List;

public interface OfferAPICallback {
    void onSuccess(List<Offer> response);
    void onError(Exception e);
}
