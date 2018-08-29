package com.reddit.di.global

import android.app.Application
import android.content.Context
import com.reddit.RedditApp
import com.reddit.data.repositories.NewsRepositoryImpl
import com.reddit.domain.repositories.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: RedditApp) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication() : Application = app

    @Provides
    @Singleton
    fun provideNewsRepository(newsRepository: NewsRepositoryImpl) : NewsRepository {
        return newsRepository
    }
}
