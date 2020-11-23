package com.example.fyberchallenge.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.ads.identifier.AdvertisingIdClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyberchallenge.R;
import com.example.fyberchallenge.SharedPrefs;
import com.example.fyberchallenge.Utils;
import com.example.fyberchallenge.adapter.OffersListAdapter;
import com.example.fyberchallenge.api.ApiInterface;
import com.example.fyberchallenge.api.ApiService;
import com.example.fyberchallenge.model.Offer;
import com.example.fyberchallenge.model.Offers;
import com.example.fyberchallenge.model.Thumbnail;
import com.example.fyberchallenge.service.OfferAPICallback;
import com.example.fyberchallenge.viewModel.MyViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    Button btn_offers;
    NavController navController;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private static final String TAG = "HomeFragment";
    private Context context;
    TextInputEditText application_id;
    TextInputEditText user_id;
    TextInputEditText security_id;
    MyViewModel viewModel;
    SharedPrefs prefs;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        btn_offers = view.findViewById(R.id.btn_offers);
        recyclerView = view.findViewById(R.id.lv_offers);
        application_id = view.findViewById(R.id.et_application_id);
        application_id.setText(Utils.app_id);
        user_id = view.findViewById(R.id.et_user_id);
        user_id.setText(Utils.user_id_name);
        security_id = view.findViewById(R.id.et_security_id);
        progressBar = view.findViewById(R.id.progress_circular);
        security_id.setText(Utils.token);
        prefs = new SharedPrefs(context);
        viewModel = ViewModelProviders.of((FragmentActivity) context).get(MyViewModel.class);
        btn_offers.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getOffers(new OfferAPICallback() {
                @Override
                public void onSuccess(List<Offer> response) {
                    progressBar.setVisibility(View.INVISIBLE);
                    navController.navigate(R.id.action_homeFragment_to_listFragment);
                    prefs.saveSharedPref("app_id", Objects.requireNonNull(application_id.getText()).toString());
                    prefs.saveSharedPref("user_id", Objects.requireNonNull(user_id.getText()).toString());
                    prefs.saveSharedPref("security_id", Objects.requireNonNull(security_id.getText()).toString());
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(context,"Cannot retrieve offers list", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}