package com.example.fyberchallenge;

import com.example.fyberchallenge.model.Offer;
import com.example.fyberchallenge.service.IOfferService;
import com.example.fyberchallenge.service.OfferAPICallback;
import com.example.fyberchallenge.service.OfferServiceImpl;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ServiceUnitTest {

    IOfferService offerService = new OfferServiceImpl();

    @Test
    public void api_should_return_success_in_10s() {

        final boolean[] isSuccess = {false};

        try {
            //set time in milli
            offerService.getOffers(new OfferAPICallback() {
                @Override
                public void onSuccess(List<Offer> response) {
                    isSuccess[0] = true;
                }

                @Override
                public void onError(Exception e) {
                    isSuccess[0] = false;
                }
            });
            Thread.sleep(10000);

            assertTrue(isSuccess[0]);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void api_should_not_return_empty_data() {

        final boolean[] isSuccess = {false};

        try {
            //set time in milli
            offerService.getOffers(new OfferAPICallback() {
                @Override
                public void onSuccess(List<Offer> response) {
                    isSuccess[0] = response != null && !response.isEmpty();
                }

                @Override
                public void onError(Exception e) {
                    isSuccess[0] = false;
                }
            });

            Thread.sleep(10000);
            assertTrue(isSuccess[0]);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}