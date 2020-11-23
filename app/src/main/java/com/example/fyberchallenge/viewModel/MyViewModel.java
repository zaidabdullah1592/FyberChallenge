package com.example.fyberchallenge.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fyberchallenge.model.Offer;
import com.example.fyberchallenge.service.IOfferService;
import com.example.fyberchallenge.service.OfferAPICallback;
import com.example.fyberchallenge.service.OfferServiceImpl;

import java.util.List;

public class MyViewModel extends ViewModel {

    MutableLiveData<List<Offer>> offersMutableLiveData;
    List<Offer> offersList;
    IOfferService offerService = new OfferServiceImpl();

    public MyViewModel() {
        offersMutableLiveData = new MutableLiveData<>();
        init();
    }

    public MutableLiveData<List<Offer>> getOffersMutableLiveData() {
        return offersMutableLiveData;
    }

    public void init() {
        offersMutableLiveData.setValue(offersList);
    }

    public void getOffers(OfferAPICallback offerAPICallback) {
        offerService.getOffers(new OfferAPICallback() {
            @Override
            public void onSuccess(List<Offer> response) {
                offersMutableLiveData.setValue(offersList);
                offerAPICallback.onSuccess(response);
            }

            @Override
            public void onError(Exception e) {
                offerAPICallback.onError(e);
                e.printStackTrace();
            }
        });
    }
}
