package com.reddit.presentation.ui.global.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri

object Launcher {
    fun openUrl(activity: Activity, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        activity.startActivity(intent)
    }
}