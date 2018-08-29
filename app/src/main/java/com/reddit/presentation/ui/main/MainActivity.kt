package com.reddit.presentation.ui.main

import android.os.Bundle
import com.reddit.R
import com.reddit.RedditApp
import com.reddit.domain.RedditNewsItem
import com.reddit.presentation.mvp.main.MainViewModel
import com.reddit.presentation.ui.global.AbsMvvmActivity
import com.reddit.presentation.ui.global.utils.Launcher
import com.reddit.presentation.ui.global.utils.initNewsList
import com.reddit.presentation.ui.main.adapter.NewsAdapter
import com.reddit.presentation.ui.main.adapter.NewsDelegateAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbsMvvmActivity<MainViewModel>(), NewsDelegateAdapter.OnViewSelectedListener {
    private lateinit var adapter: NewsAdapter

    override fun provideViewModelClass() = MainViewModel::class.java

    override fun provideLayoutResId() = R.layout.activity_main

    override fun initInjection() {
        RedditApp.mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActionBar()

        setupRecyclerView()
        loadNews()
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.reddit_icon)
        supportActionBar?.title = " " + getString(R.string.app_name)
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter(this)
        rvNews.initNewsList { loadNewsList() }
        rvNews.adapter = adapter
    }

    private fun loadNews() {
        adapter.addNews(viewModel.getNewsListOrEmpty())
        viewModel.error.observeLiveDataNullSafety(this::onLastNewError)
        viewModel.lastNews.observeLiveDataNullSafety(this::onLastNewsAdded)
    }

    private fun onLastNewsAdded(news: List<RedditNewsItem>) {
        adapter.addNews(news)
    }

    private fun onLastNewError(error: String) {
        showRetrySnackbar(rvNews, error) {
            loadNewsList()
        }
    }

    override fun onItemSelected(url: String?) {
        if (url.isNullOrEmpty()) {
            showSnackbar(rvNews, getString(R.string.error_invalid_news_url))
        } else {
            Launcher.openUrl(this, url!!)
        }
    }

    private fun loadNewsList() {
        viewModel.getOlderNews()
    }
}
