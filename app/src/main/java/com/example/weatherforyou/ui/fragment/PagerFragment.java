package com.example.weatherforyou.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.example.weatherforyou.App;
import com.example.weatherforyou.R;
import com.example.weatherforyou.ui.adapter.WeatherPagerStateAdapter;
import com.example.weatherforyou.viewmodel.PagerViewModel;
import com.example.weatherforyou.viewmodel.PermissionViewModel;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import javax.inject.Inject;


//в этом фрагменте получаем список наших городов и запускаем фрагменты с информацией о погоде
public class PagerFragment extends Fragment {

    //отсюда получаем данные для отображения
    @Inject
    PagerViewModel pagerViewModel;


    //получаем разрешение на использование геолокации
    @Inject
    PermissionViewModel permissionViewModel;



    private ViewPager2 viewPager;

    //красивые индикаторы текущей страницы
    SpringDotsIndicator dotsIndicator;

    private WeatherPagerStateAdapter weatherPagerStateAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        App.AppInstance.getInstance().getPagerFragmentComponent().inject(this);
        return inflater.inflate(R.layout.fragment_pager_container, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pagerViewModel.onViewReady();

        viewPager = view.findViewById(R.id.view_pager);

        dotsIndicator = view.findViewById(R.id.spring_dots_indicator);
    }

    private void initViewPager(int cityValue) {

        weatherPagerStateAdapter = new WeatherPagerStateAdapter(getChildFragmentManager(), getLifecycle(), cityValue);

        viewPager.setAdapter(weatherPagerStateAdapter);
        dotsIndicator.setViewPager2(viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        //количесво горов в базе
        pagerViewModel.getLiveFavoriteCityCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer cityCount) {
                //если 0 загружаем стартовую страницу
                if (cityCount == 0) {
                    Log.e("navigate", "to welcome fragment");
                    Navigation.findNavController(getView()).navigate(R.id.welcomeFragment);
                } else {
                    initViewPager(cityCount);


                }
            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
