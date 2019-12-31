package com.example.daggermvvmex.di.module;

import com.example.daggermvvmex.ui.main.MainActivity;
import com.example.daggermvvmex.ui.main.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
