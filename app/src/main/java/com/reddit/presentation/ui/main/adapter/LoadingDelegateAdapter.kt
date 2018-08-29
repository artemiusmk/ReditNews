package com.reddit.presentation.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.reddit.R
import com.reddit.presentation.global.extensions.inflate
import com.reddit.presentation.ui.global.adapter.ViewType
import com.reddit.presentation.ui.global.adapter.ViewTypeDelegateAdapter

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) =
            LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item_loading))
}