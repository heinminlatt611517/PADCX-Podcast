package com.example.padcx_podcast_monthly_assignment.mvp.view

import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.shared.mvp.view.BaseView

interface HomeView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun showNowPlayingPodCast(podCastEpisode : UpNextPodCastDataVO)
    fun showUpNextPodCastlists(podCastPlaylists: ArrayList<UpNextPodCastDataVO>)
    fun onTapDownloadButton(podCastData : UpNextPodCastDataVO)
    //fun navigateToDetailScreen(audio : String,title : String,image : String,description : String)
    fun navigateToDetailScreen(id : String)
    fun showDownloadPodcastItem(data: UpNextPodCastDataVO)

}