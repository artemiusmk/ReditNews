package com.reddit.presentation.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.reddit.R
import com.reddit.RedditApp
import com.reddit.presentation.ui.global.InfiniteScrollListener
import com.reddit.presentation.global.extensions.androidLazy
import com.reddit.presentation.ui.main.adapter.NewsAdapter
import com.reddit.presentation.ui.main.adapter.NewsDelegateAdapter
import com.reddit.presentation.mvp.main.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NewsDelegateAdapter.OnViewSelectedListener {

    private val newsAdapter by androidLazy { NewsAdapter(this) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()

        RedditApp.mainComponent.inject(this)

        setupRecyclerView()
        setupViewModel()
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.reddit_icon)
        supportActionBar?.title = " " + getString(R.string.app_name)
    }

    private fun setupRecyclerView() {
        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({
                viewModel?.getOlderNews() }, linearLayout))
        }
        news_list.adapter = newsAdapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel?.lastNews?.observe(this, Observer {
            if (it != null) {
                newsAdapter.addNews(it)
            }
        })
        viewModel?.error?.observe(this, Observer {
            Snackbar.make(news_list, it.orEmpty(), Snackbar.LENGTH_LONG)
                    .setAction("RETRY") { viewModel?.getOlderNews() }
                    .show()
        })
        val lastNews = viewModel?.news?.value.orEmpty()
        newsAdapter.addNews(lastNews.flatMap { it.news })
    }

    override fun onItemSelected(url: String?) {
        if (url.isNullOrEmpty()) {
            Snackbar.make(news_list, "No URL assigned to this news", Snackbar.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}
