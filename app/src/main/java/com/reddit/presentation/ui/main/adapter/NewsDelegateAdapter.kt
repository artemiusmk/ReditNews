package com.reddit.presentation.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.reddit.R
import com.reddit.domain.RedditNewsItem
import com.reddit.presentation.global.extensions.getFriendlyTime
import com.reddit.presentation.global.extensions.inflate
import com.reddit.presentation.global.extensions.loadImg
import com.reddit.presentation.ui.global.adapter.ViewType
import com.reddit.presentation.ui.global.adapter.ViewTypeDelegateAdapter
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter(val viewActions: OnViewSelectedListener) : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)) {

        private val imgThumbnail = itemView.img_thumbnail
        private val description = itemView.description
        private val author = itemView.author
        private val comments = itemView.comments
        private val time = itemView.time

        fun bind(item: RedditNewsItem) {
            imgThumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()

            super.itemView.setOnClickListener { viewActions.onItemSelected(item.url)}
        }
    }

    interface OnViewSelectedListener {
        fun onItemSelected(url: String?)
    }
}