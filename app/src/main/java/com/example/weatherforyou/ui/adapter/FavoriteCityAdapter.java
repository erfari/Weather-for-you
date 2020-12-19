package com.example.weatherforyou.ui.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforyou.R;
import com.example.weatherforyou.databinding.ItemCityInfoBinding;
import com.example.weatherforyou.databinding.ItemDailyWeatherBinding;
import com.example.weatherforyou.model.city.CityImpl;
import com.example.weatherforyou.model.city.FavoriteCity;
import com.example.weatherforyou.model.weather.DailyWeather;
import com.example.weatherforyou.repository.CityRepository;
import com.example.weatherforyou.ui.adapter.listeners.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteCityAdapter extends RecyclerView.Adapter<FavoriteCityAdapter.FavoriteCityViewHolder> {

    private List<FavoriteCity> items = new ArrayList<>();

    private CityRepository cityRepository;

    private Context context;

    private ItemClickListener<FavoriteCity> itemClickListener;

    public FavoriteCityAdapter(Context context, ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.context = context;
        this.cityRepository = new CityRepository(context);
    }

    public void setItems(List<FavoriteCity> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemCityInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_city_info, parent, false);

        return new FavoriteCityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteCityViewHolder holder, int position) {
        holder.bind(items.get(position), context, itemClickListener);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class FavoriteCityViewHolder extends RecyclerView.ViewHolder {

        ItemCityInfoBinding weatherBinding;

        public FavoriteCityViewHolder(ItemCityInfoBinding binding) {
            super(binding.getRoot());
            weatherBinding = binding;

        }

        public void bind(FavoriteCity favoriteCity , Context context, ItemClickListener<FavoriteCity> cityItemClickListener) {

            weatherBinding.setFavoritecity(favoriteCity);

            ConstraintLayout constraintLayout =  itemView.findViewById(R.id.city_container);

            constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    cityItemClickListener.onClick(favoriteCity);
                    return false;
                }
            });
            constraintLayout.setBackground(new ColorDrawable(favoriteCity.backColor()));



        }
    }
}




