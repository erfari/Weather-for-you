package com.example.weatherforyou.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforyou.R;
import com.example.weatherforyou.model.city.CityImpl;
import com.example.weatherforyou.model.city.FavoriteCity;
import com.example.weatherforyou.repository.CityRepository;
import com.example.weatherforyou.ui.adapter.FavoriteCityAdapter;
import com.example.weatherforyou.ui.adapter.WeatherPagerStateAdapter;
import com.example.weatherforyou.viewmodel.FavoriteCityViewModel;
import com.example.weatherforyou.viewmodel.PermissionViewModel;
import com.example.weatherforyou.viewmodel.WeatherViewModel;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FavoriteCityFragment extends Fragment {

    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    private RecyclerView recyclerView;

    private View settingButton;
    private View addCityButton;

    private FavoriteCityAdapter adapter;

    private CityRepository repository;

    private FavoriteCityViewModel favoriteCityViewModel;

    private LiveData<FavoriteCity> favoriteCityLiveData;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        favoriteCityViewModel = new ViewModelProvider(this).get(FavoriteCityViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repository  = new CityRepository( getContext());
        init(view);

        favoriteCityViewModel.getClickedCity().observe(getViewLifecycleOwner(), new Observer<FavoriteCity>() {
            @Override
            public void onChanged(FavoriteCity favoriteCity) {
                final AlertDialog.Builder dialog = new  AlertDialog.Builder(getContext());
                dialog.setTitle("Delete?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               favoriteCityViewModel.deleteCity(favoriteCity);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                dialog.create();
                dialog.show();

            }
        });
        adapter = new FavoriteCityAdapter(getContext(), favoriteCityViewModel);

        favoriteCityViewModel.updateCityList();

        favoriteCityViewModel.getLiveFavoriteCity().observe(getViewLifecycleOwner(), new Observer<List<FavoriteCity>>() {
            @Override
            public void onChanged(List<FavoriteCity> favoriteCities) {
                adapter.setItems(favoriteCities);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        settingButton.setOnClickListener(view1 -> Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.settingFragment));



        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        List<Place.Field> fields = Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this.getContext());

        addCityButton.setOnClickListener(view1 -> startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE));


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Log.e("navigate", "to pagerFragment");
                Navigation.findNavController(view).navigate(R.id.pagerFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.city_recycler_view);
        settingButton = view.findViewById(R.id.setting_button);
        addCityButton = view.findViewById(R.id.add_new_city);

    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.setItems(repository.getFavoriteCities());

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Toast.makeText(getContext(), place.getName() + " " + place.getLatLng(), Toast.LENGTH_SHORT).show();
                repository.saveCity(place);
                adapter.setItems(repository.getFavoriteCities());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("TAG", status.getStatusMessage());
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }





}
