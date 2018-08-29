package com.reddit.presentation.mvp.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.reddit.domain.RedditNews
import com.reddit.domain.RedditNewsItem
import com.reddit.domain.main.MainInteractor
import com.reddit.presentation.global.utils.SingleLiveEvent
import com.reddit.presentation.mvp.global.ErrorHandler
import com.reddit.presentation.mvp.global.SchedulersProvider
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel
@Inject constructor(
        private var mainInteractor: MainInteractor,
        private val schedulersProvider: SchedulersProvider,
        private val errorHandler: ErrorHandler
) : ViewModel() {
    val error = SingleLiveEvent<String>()
    val lastNews = SingleLiveEvent<List<RedditNewsItem>>()
    private val news = MutableLiveData<List<RedditNews>>()
    private var disposable: Disposable? = null
    private var cachedNews = mutableListOf<RedditNews>()
    private val after: String
        get() = news.value?.last()?.after.orEmpty()

    init {
        getOlderNews()
    }

    fun getOlderNews() {
        disposable?.dispose()
        disposable = mainInteractor.getOlderNews(after)
                .compose(schedulersProvider.getIOToMainTransformer())
                .subscribe(
                        this::onNewsLoaded,
                        this::onNewsLoadingFailure)
    }

    private fun onNewsLoaded(redditNews: RedditNews) {
        cachedNews.add(redditNews)
        news.value = cachedNews
        lastNews.value = redditNews.news
    }

    fun getNewsListOrEmpty(): List<RedditNewsItem> {
        return news.value.orEmpty().flatMap { it.news }
    }

    private fun onNewsLoadingFailure(e: Throwable) {
        error.value = errorHandler.parseError(e)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}