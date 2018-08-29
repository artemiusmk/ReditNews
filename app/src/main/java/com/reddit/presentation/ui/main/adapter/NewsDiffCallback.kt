package com.reddit.presentation.ui.main.adapter

import android.support.v7.util.DiffUtil

import com.reddit.domain.RedditNewsItem

class NewsDiffCallback(private var newItems: List<RedditNewsItem>, private var oldItems: List<RedditNewsItem>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].title == newItems[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].title == newItems[newItemPosition].title
    }
}

