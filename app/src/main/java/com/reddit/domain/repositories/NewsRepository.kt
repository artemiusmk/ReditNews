package com.reddit.domain.repositories

import com.reddit.domain.RedditNews
import io.reactivex.Observable

interface NewsRepository {

    fun getOlderNews(after: String, limit: String): Observable<RedditNews>
}