package com.example.padcx_podcast_monthly_assignment.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.mvp.view.HomeView
import com.example.shared.mvp.presenter.BasePresenter

interface HomePresenter : BasePresenter<HomeView>{
    fun onUIReady(lifecycleOwner: LifecycleOwner)
}