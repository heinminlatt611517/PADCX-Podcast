package com.example.padcx_podcast_monthly_assignment.mvp.view

import com.example.padcx_podcast_monthly_assignment.data.vos.DetailVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.shared.mvp.view.BaseView

interface DetailView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun displayPodCastEpisode(data : UpNextPodCastDataVO)
}