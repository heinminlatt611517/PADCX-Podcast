package com.example.padcx_podcast_monthly_assignment.utils

import java.util.*


fun Int.stringForTime(formatter : Formatter) : String? {
    var mFormatter = formatter
    val mFormatBuilder: StringBuilder = StringBuilder()
    mFormatter = Formatter(mFormatBuilder, Locale.getDefault())
    val totalSeconds = this / 1000
    val seconds = totalSeconds % 60
    val minutes = totalSeconds / 60 % 60
    val hours = totalSeconds / 3600
    mFormatBuilder.setLength(0)
    return if (hours > 0) {
        mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
    } else {
        mFormatter.format("%02d:%02d", minutes, seconds).toString()
    }
}
