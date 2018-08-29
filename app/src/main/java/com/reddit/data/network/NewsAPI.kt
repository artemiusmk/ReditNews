package com.reddit.data.network

import com.reddit.data.RedditNewsResponse
import io.reactivex.Observable

interface NewsAPI {
    fun getNews(after: String, limit: String): Observable<RedditNewsResponse>
}