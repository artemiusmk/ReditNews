@file:JvmName("TimeUtils")

package com.reddit.presentation.global.extensions

import java.util.*

fun Long.getAgoTimeFromSec(): String {
    val dateTime = Date(this * 1000)
    val sb = StringBuffer()
    val current = Calendar.getInstance().time

    // diffTime in minutes
    val min = ((current.time - dateTime.time) / 1000).toInt() / 60
    val hrs = min / 60
    val days = hrs / 24
    val months = days / 30
    val years = months / 12

    when {
        years > 0 -> sb.append("$years years")
        months > 0 -> sb.append("$months months")
        days > 0 -> sb.append("$days days")
        hrs > 0 -> sb.append("$hrs hours")
        min > 0 -> sb.append("$min minutes")
    }

    if (sb.isEmpty())
        sb.append("less than a minute")
    sb.append(" ago")

    return sb.toString()
}