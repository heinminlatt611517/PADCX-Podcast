package com.example.padcx_podcast_monthly_assignment.mvp.view

import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.view.BaseView

interface HomeView : BaseView {
    fun enableSwipeRefresh()
    fun disableSwipeRefresh()
    fun showLoading()
    fun hideLoading()
    fun showUpNextPodCastLists()

}