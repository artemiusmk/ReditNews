package com.reddit.presentation.ui.global.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.reddit.presentation.ui.global.InfiniteScrollListener

fun RecyclerView.initNewsList(scrollAction: () -> Unit) {
    val linearLayout = LinearLayoutManager(context)
    this.layoutManager = linearLayout
    this.clearOnScrollListeners()
    this.addOnScrollListener(InfiniteScrollListener(linearLayout, scrollAction))
    this.setHasFixedSize(true)
}