package com.example.weatherforyou.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.example.weatherforyou.App;
import com.example.weatherforyou.R;
import com.example.weatherforyou.viewmodel.WelcomeViewModel;

import javax.inject.Inject;

public class WelcomeFragment extends Fragment {


    private Button cityButton, locationButton;
    private ProgressBar loadByLocationProgressBar;

    @Inject
    WelcomeViewModel welcomeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        App.AppInstance.getInstance().getPagerFragmentComponent().inject(this);
        return inflater.inflate(R.layout.fragment_no_one_city_choice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadByLocationProgressBar = view.findViewById(R.id.progressBar);

        cityButton = view.findViewById(R.id.city_button);

        cityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.favoriteCityFragment);

            }
        });

        locationButton = view.findViewById(R.id.location_button);

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                welcomeViewModel.loadCityFromMyLocation();

                welcomeViewModel.getNavigateUp().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {

                        if (aBoolean) {
                            Navigation.findNavController(view).navigate(R.id.pagerFragment);

                        }
                    }
                });
            }
        });

        welcomeViewModel.getIsShowProgressBar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    loadByLocationProgressBar.setVisibility(View.VISIBLE);
                }else {
                    loadByLocationProgressBar.setVisibility(View.GONE);
                }
            }
        });



    }
}
