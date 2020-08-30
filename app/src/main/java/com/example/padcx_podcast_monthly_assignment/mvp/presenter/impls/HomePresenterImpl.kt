package com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.HomePresenter
import com.example.padcx_podcast_monthly_assignment.mvp.view.HomeView
import com.example.shared.mvp.presenter.AbstractBasePresenter

class HomePresenterImpl : HomePresenter,AbstractBasePresenter<HomeView>() {
    override fun onUIReady(lifecycleOwner: LifecycleOwner) {
    }


}