package com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.DownloadPodcastPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.view.DownloadPodcastView
import com.example.shared.mvp.presenter.AbstractBasePresenter

class DownloadPodcastPresenterImpl : DownloadPodcastPresenter,AbstractBasePresenter<DownloadPodcastView>() {
    override fun onUIReady(lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapFindSomethingNew() {
        mView?.showDownloadPodCast()
        Log.d("FindSomethingNew","Tap Find Some New")
    }

    override fun onTapReload() {
        mView?.showDownloadPodCast()
        Log.d("Reload","Tap Reload New")
    }
}