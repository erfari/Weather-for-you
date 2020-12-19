package com.example.weatherforyou.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforyou.R;
import com.example.weatherforyou.databinding.ItemHourWeatherBinding;
import com.example.weatherforyou.model.weather.HourWeather;

import java.util.ArrayList;
import java.util.List;

public class HourWeatherAdapter extends RecyclerView.Adapter<HourWeatherViewHolder> {

    private List<HourWeather> items = new ArrayList<>();


    public void setItems(List<HourWeather> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HourWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemHourWeatherBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_hour_weather, parent, false);


        return new HourWeatherViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull HourWeatherViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class HourWeatherViewHolder extends RecyclerView.ViewHolder {

    ItemHourWeatherBinding weatherBinding;

    public HourWeatherViewHolder(ItemHourWeatherBinding binding) {
        super(binding.getRoot());
        weatherBinding = binding;

    }

    public void bind(HourWeather weather){
        weatherBinding.setHourWeather(weather);
    }
}
