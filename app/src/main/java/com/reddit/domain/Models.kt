package com.reddit.domain

import com.reddit.presentation.ui.global.adapter.NEWS
import com.reddit.presentation.ui.global.adapter.ViewType

data class RedditNews(
        val after: String,
        val before: String,
        val news: List<RedditNewsItem>
)

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String?
) : ViewType {
    override fun getViewType() = NEWS
}