package com.example.padcx_podcast_monthly_assignment.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.mvp.view.DownloadPodcastView
import com.example.padcx_podcast_monthly_assignment.views.viewHolders.DownloadPodcastViewHolder
import com.example.padcx_podcast_monthly_assignment.views.viewPods.EmptyViewPod
import com.example.shared.mvp.presenter.BasePresenter

interface DownloadPodcastPresenter : BasePresenter<DownloadPodcastView>,EmptyViewPod.Delegate,DownloadPodcastViewHolder.DownloadDelegate {
    fun onUIReady(lifecycleOwner: LifecycleOwner)
}