package com.example.padcx_podcast_monthly_assignment.utils

import android.app.DownloadManager
import android.content.Context
import android.media.Image
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import java.io.File
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

fun String.createDocumentFile() : File{
    return File(this)
}

fun ImageView.glideImageLoader(context: Context,imageUrl : String){
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}

fun startDownloading(context: Context,data : UpNextPodCastDataVO)
{
    //download request
    val request  = DownloadManager.Request(Uri.parse(data.UpNextAudio))
        .apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            setTitle(data.UpNextTitle)
            setDescription(data.UpNextDescription)
            allowScanningByMediaScanner()
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,".mp3")
        }
    //get download service , and enqueue file
    val manager= context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    manager.enqueue(request)
}
