package com.example.weatherforyou.model.drawableresource;

import com.bumptech.glide.Glide;
import com.example.weatherforyou.R;

public class DrawableResourceFactory {

    public DrawableResource getDrawable(String weatherType){
        switch (weatherType) {
            case "Thunderstorm":
            case "Drizzle":
            case "Rain":
               return new RainDrawable();
            case "Snow":
               return new SnowDrawable();
            case "Clear":
              return new SunDrawable();
            case "Clouds":
               return new CloudDrawable();
        }
        return null;
    }
}
