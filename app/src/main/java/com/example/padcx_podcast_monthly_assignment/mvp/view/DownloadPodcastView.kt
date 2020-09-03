package com.example.padcx_podcast_monthly_assignment.mvp.view

import com.example.padcx_podcast_monthly_assignment.data.vos.DownloadPodCastDataVO
import com.example.shared.mvp.view.BaseView

interface DownloadPodcastView : BaseView{
    fun showDownloadPodCast(downloadPodCastList : List<DownloadPodCastDataVO>)

    fun navigateToPodCastDetailScreen(id : String)

}