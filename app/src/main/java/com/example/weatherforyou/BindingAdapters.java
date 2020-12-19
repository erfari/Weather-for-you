package com.example.weatherforyou;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BindingAdapters {





    @BindingAdapter("app:src")
    public static void setImage(ImageView imageView, String iconId) {
        String url = "https://openweathermap.org/img/wn/" + iconId + "@2x.png";
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }


}