package com.example.padcx_podcast_monthly_assignment.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.mvp.view.DownloadPodcastView
import com.example.padcx_podcast_monthly_assignment.views.viewPods.EmptyViewPod
import com.example.shared.mvp.presenter.BasePresenter

interface DownloadPodcastPresenter : BasePresenter<DownloadPodcastView>,EmptyViewPod.Delegate {
    fun onUIReady(lifecycleOwner: LifecycleOwner)
}