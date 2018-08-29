package com.reddit.di.main

import com.reddit.di.global.AppModule
import com.reddit.di.global.NetworkModule
import com.reddit.di.global.ViewModelModule
import com.reddit.presentation.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MainModule::class, NetworkModule::class, ViewModelModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}