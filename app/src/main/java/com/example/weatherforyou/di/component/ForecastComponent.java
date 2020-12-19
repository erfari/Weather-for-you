package com.example.weatherforyou.di.component;


import com.example.weatherforyou.di.module.ForecastModule;
import com.example.weatherforyou.di.module.PagerFragmentModule;
import com.example.weatherforyou.di.scope.FunctionScope;

import dagger.Subcomponent;

@FunctionScope
@Subcomponent(modules = {ForecastModule.class})
public interface ForecastComponent {

    PagerFragmentComponent plus(PagerFragmentModule pagerFragmentModule);

}
