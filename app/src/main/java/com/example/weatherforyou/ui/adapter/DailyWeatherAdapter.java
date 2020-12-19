package com.example.weatherforyou.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforyou.R;
import com.example.weatherforyou.databinding.ItemDailyWeatherBinding;
import com.example.weatherforyou.model.weather.DailyWeather;

import java.util.ArrayList;
import java.util.List;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherViewHolder> {

    private List<DailyWeather> items = new ArrayList<>();

    public void setItems(List<DailyWeather> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DailyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemDailyWeatherBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_daily_weather, parent, false);

        return new DailyWeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyWeatherViewHolder holder, int position) {
        holder.bind(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class DailyWeatherViewHolder extends RecyclerView.ViewHolder {

    ItemDailyWeatherBinding weatherBinding;

    public DailyWeatherViewHolder(ItemDailyWeatherBinding binding) {
        super(binding.getRoot());
        weatherBinding = binding;
    }

    public void bind(DailyWeather weather) {
        weatherBinding.setDailyWeather(weather);
    }
}
