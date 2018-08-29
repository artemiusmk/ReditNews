package com.reddit.presentation.mvp.global

import com.reddit.BuildConfig
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandler @Inject constructor() {

    fun parseError(throwable: Throwable): String {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace()
        }
        return if (throwable is ConnectException || throwable is UnknownHostException) {
            "Network error. Check your connection"
        } else {
            "App error. Operation failed"
        }
    }
}
