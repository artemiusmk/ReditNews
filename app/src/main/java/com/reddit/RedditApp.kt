package com.reddit

import android.app.Application
import com.reddit.di.global.AppModule
import com.reddit.di.main.DaggerMainComponent
import com.reddit.di.main.MainComponent

class RedditApp : Application() {

    companion object {
        lateinit var mainComponent: MainComponent
    }

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}