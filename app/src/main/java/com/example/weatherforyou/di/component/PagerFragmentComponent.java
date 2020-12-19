package com.example.weatherforyou.di.component;

import com.example.weatherforyou.di.module.PagerFragmentModule;
import com.example.weatherforyou.di.scope.FragmentScope;
import com.example.weatherforyou.ui.MainActivity;
import com.example.weatherforyou.ui.fragment.PagerFragment;
import com.example.weatherforyou.ui.fragment.WelcomeFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {PagerFragmentModule.class})
public interface PagerFragmentComponent {

    void inject(PagerFragment fragment);
    void inject(WelcomeFragment fragment);
}
