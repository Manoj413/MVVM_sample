package com.example.daggermvvmex.ui.main;

import com.example.daggermvvmex.ui.list.DetailFragment;
import com.example.daggermvvmex.ui.list.ListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract DetailFragment provideDetailsFragment();
}