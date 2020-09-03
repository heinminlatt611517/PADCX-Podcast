package com.example.padcx_podcast_monthly_assignment.mvp.view

import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastPlaylistsVO
import com.example.shared.mvp.view.BaseView

interface HomeView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun showNowPlayingPodCast(podCastEpisode : PodCastDataVO)
    fun showUpNextPodCastlists(podCastPlaylists : UpNextPodCastPlaylistsVO)
    fun onTapDownloadButton(podCastData : UpNextPodCastDataVO)
    fun navigateToDetailScreen(id : String)

}