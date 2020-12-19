package com.example.weatherforyou.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforyou.R;
import com.example.weatherforyou.databinding.ItemHourWeatherBinding;
import com.example.weatherforyou.databinding.ItemWeatherInfoBinding;

import com.example.weatherforyou.model.weather.WeatherInfoUIModel;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfoAdapter extends RecyclerView.Adapter<WeatherInfoViewHolder> {

    private List<WeatherInfoUIModel> items = new ArrayList<>();


    public void setItems(List<WeatherInfoUIModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemWeatherInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_weather_info, parent, false);


        return new WeatherInfoViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull WeatherInfoViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class WeatherInfoViewHolder extends RecyclerView.ViewHolder {

    ItemWeatherInfoBinding weatherBinding;

    public WeatherInfoViewHolder(ItemWeatherInfoBinding binding) {
        super(binding.getRoot());
        weatherBinding = binding;

    }

    public void bind(WeatherInfoUIModel weather) {
        weatherBinding.setWeatherInfo(weather);
    }
}