package com.example.weatherforyou.ui.adapter;

import android.content.Context;
import android.location.LocationManager;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weatherforyou.databinding.ItemWeatherInfoBinding;
import com.example.weatherforyou.model.weather.WeatherInfoUIModel;
import com.example.weatherforyou.repository.CityRepository;
import com.example.weatherforyou.ui.fragment.WeatherFragment;

public class WeatherPagerStateAdapter extends FragmentStateAdapter {


    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    private int itemCount;

    public WeatherPagerStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, int itemCount) {
        super(fragmentManager, lifecycle);

        this.itemCount = itemCount;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return  WeatherFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }
}

