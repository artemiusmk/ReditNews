package com.reddit.data.repositories

import com.reddit.data.RedditNewsResponse
import com.reddit.data.network.NewsAPI
import com.reddit.domain.repositories.NewsRepository
import com.reddit.domain.RedditNews
import com.reddit.domain.RedditNewsItem
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api: NewsAPI): NewsRepository {

    override fun getOlderNews(after: String, limit: String): Observable<RedditNews> {
        return api.getNews(after, limit).map { mapToRedditNews(it) }
    }

    private fun mapToRedditNews(response: RedditNewsResponse): RedditNews {
        val dataResponse = response.data
        val news = dataResponse.children.map {
            val item = it.data
            RedditNewsItem(item.author, item.title, item.num_comments,
                    item.created, item.thumbnail, item.url)
        }
        return RedditNews(
                dataResponse.after.orEmpty(),
                dataResponse.before.orEmpty(),
                news)
    }
}