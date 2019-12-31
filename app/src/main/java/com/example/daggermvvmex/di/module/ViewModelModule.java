package com.example.daggermvvmex.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.daggermvvmex.di.util.ViewModelKey;
import com.example.daggermvvmex.ui.list.DetailViewModel;
import com.example.daggermvvmex.ui.list.ListViewModel;
import com.example.daggermvvmex.util.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindDetailsViewModel(DetailViewModel detailsViewModel);
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
