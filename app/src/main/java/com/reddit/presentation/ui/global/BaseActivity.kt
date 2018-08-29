package com.reddit.presentation.ui.global

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.reddit.R

@Suppress("unused")
abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun provideLayoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(provideLayoutResId())
    }

    final override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    protected open fun showRetrySnackbar(view: View, msg: String, action: () -> Unit) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction(R.string.msg_retry) { action() }
                .show()
    }

    protected open fun showSnackbar(view: View, @StringRes msgResId: Int, length: Int = Snackbar.LENGTH_SHORT) {
        showSnackbar(view, getString(msgResId), length)
    }

    protected open fun showSnackbar(view: View, msg: String, length: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, msg, length).show()
    }
}