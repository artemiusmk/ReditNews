package com.reddit.di.global

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.reddit.di.scopes.ViewModelKey
import com.reddit.presentation.mvp.main.MainViewModel
import com.reddit.presentation.mvp.global.ViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(userViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}