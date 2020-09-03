package com.example.padcx_podcast_monthly_assignment.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.delegate.PodCastItemDelegate
import com.example.padcx_podcast_monthly_assignment.mvp.view.HomeView
import com.example.padcx_podcast_monthly_assignment.views.viewPods.FullPlayerViewPod
import com.example.shared.mvp.presenter.BasePresenter

interface HomePresenter : BasePresenter<HomeView>,PodCastItemDelegate{
    fun onUIReady(lifecycleOwner: LifecycleOwner)

    fun downLoadAudio(podCast : UpNextPodCastDataVO)
}