package com.example.padcx_podcast_monthly_assignment.mvp.presenter

import com.example.padcx_podcast_monthly_assignment.data.vos.DetailVO
import com.example.padcx_podcast_monthly_assignment.mvp.view.DetailView
import com.example.shared.mvp.presenter.BasePresenter

interface DetailPresenter : BasePresenter<DetailView> {
    fun getPodCastEpisodeById(id : String)
}