package com.example.fyberchallenge.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyberchallenge.R;
import com.example.fyberchallenge.adapter.OffersListAdapter;
import com.example.fyberchallenge.model.Offer;
import com.example.fyberchallenge.service.OfferAPICallback;
import com.example.fyberchallenge.viewModel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    ViewModel viewModel;
    Context context;
    OffersListAdapter adapter;
    RecyclerView recyclerView;
    List<Offer> offersList = new ArrayList<>();

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        adapter = new OffersListAdapter(context, offersList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.lv_offers);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of((FragmentActivity) context).get(MyViewModel.class);
        ((MyViewModel) viewModel).getOffersMutableLiveData().observe((LifecycleOwner) context, userListUpdateObserver);
        ((MyViewModel) viewModel).getOffers(new OfferAPICallback() {
            @Override
            public void onSuccess(List<Offer> response) {
                setData(response);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context, "Unable to load data:" + e, Toast.LENGTH_LONG).show();
            }
        });
    }


    Observer<List<Offer>> userListUpdateObserver = new Observer<List<Offer>>() {
        @Override
        public void onChanged(List<Offer> offers) {
            setData(offers);
        }
    };

    private void setData(List<Offer> offers) {
        if (offersList == null || offers == null) return;
        offersList.clear();
        offersList.addAll(offers);
        adapter.setDataAndNotify(offers);
    }
}