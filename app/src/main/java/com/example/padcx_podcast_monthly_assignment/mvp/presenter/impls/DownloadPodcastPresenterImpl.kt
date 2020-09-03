package com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.model.impls.PodCastModelImpl
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.DownloadPodcastPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.view.DownloadPodcastView
import com.example.shared.mvp.presenter.AbstractBasePresenter

class DownloadPodcastPresenterImpl : DownloadPodcastPresenter,AbstractBasePresenter<DownloadPodcastView>() {

    private val mPodCastModel : PodCastModel = PodCastModelImpl

    override fun onUIReady(lifecycleOwner: LifecycleOwner) {
        fetchDownlodaPodcast(lifecycleOwner)
    }

    private fun fetchDownlodaPodcast(lifecycleOwner: LifecycleOwner) {
          mPodCastModel.getDownloadPodCast()
              .observe(lifecycleOwner, Observer {
                  mView?.showDownloadPodCast(it)
              })
    }

    override fun onTapFindSomethingNew() {
        Log.d("FindSomethingNew","Tap Find Some New")
    }

    override fun onTapReload() {
        Log.d("Reload","Tap Reload New")
    }

    override fun onTapDownloadPodCastItem(id: String) {
        mView?.navigateToPodCastDetailScreen(id)
    }
}