package com.reddit.domain.main

import com.reddit.domain.RedditNews
import com.reddit.domain.repositories.NewsRepository
import io.reactivex.Observable
import javax.inject.Inject

class MainInteractor @Inject constructor(private var newsRepository: NewsRepository) {

    private val LIMIT_COUNT = "10"

    fun getOlderNews(after: String): Observable<RedditNews> {
        return newsRepository.getOlderNews(after, LIMIT_COUNT)
    }
}