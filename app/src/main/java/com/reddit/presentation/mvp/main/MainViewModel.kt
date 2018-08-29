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

class MainViewModel @Inject constructor(private var mainInteractor: MainInteractor,
                                        private val schedulersProvider: SchedulersProvider,
                                        private val errorHandler: ErrorHandler) : ViewModel() {

    val error = SingleLiveEvent<String>()
    val lastNews = SingleLiveEvent<List<RedditNewsItem>>()
    val news = MutableLiveData<List<RedditNews>>()
    private var disposable: Disposable? = null
    private var cachedNews = mutableListOf<RedditNews>()

    init {
        getOlderNews()
    }

    fun getOlderNews() {
        disposable?.dispose()
        disposable = mainInteractor.getOlderNews(news.value?.last()?.after.orEmpty())
                .compose(schedulersProvider.getIOToMainTransformer())
                .subscribe(
                        {
                            cachedNews.add(it)
                            news.value = cachedNews
                            lastNews.value = it.news
                        }, {
                    error.value = errorHandler.parseError(it)
                        }
                )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}