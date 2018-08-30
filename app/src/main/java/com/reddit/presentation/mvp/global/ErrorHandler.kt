package com.reddit.presentation.mvp.global

import android.content.Context
import com.reddit.BuildConfig
import com.reddit.R
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ErrorHandler @Inject constructor(private val context: Context) {

    fun parseError(throwable: Throwable): String {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace()
        }
        return if (throwable is ConnectException ||
                throwable is UnknownHostException ||
                throwable is TimeoutException) {
            context.getString(R.string.error_network)
        } else {
            context.getString(R.string.error_operation_failed)
        }
    }
}
