package com.example.padcx_podcast_monthly_assignment.delegate

import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO

interface PodCastItemDelegate {
    fun onTapUpNextPodCastItem(id : String)
    fun onTapDownloadButton(podCast : UpNextPodCastDataVO)
}