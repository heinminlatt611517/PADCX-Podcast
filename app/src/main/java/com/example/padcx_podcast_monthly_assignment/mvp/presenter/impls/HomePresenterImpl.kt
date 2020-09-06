package com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.model.impls.PodCastModelImpl
import com.example.padcx_podcast_monthly_assignment.data.vos.DownloadPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.HomePresenter
import com.example.padcx_podcast_monthly_assignment.mvp.view.HomeView
import com.example.shared.mvp.presenter.AbstractBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenterImpl : HomePresenter,AbstractBasePresenter<HomeView>() {

    private val mPodCastModel : PodCastModel = PodCastModelImpl

    override fun onUIReady(lifecycleOwner: LifecycleOwner) {
          getPreRequestPodCast(lifecycleOwner)
    }

    override fun onDownloadItem(context: Context, dataVO: UpNextPodCastDataVO) {
        mPodCastModel.startDownloadPodcast(context,dataVO)
    }

    override fun onTapUpNextPodCastItem(id: String) {
        mView?.navigateToDetailScreen(id)
    }

    override fun onTapDownloadButton(podCast: UpNextPodCastDataVO) {
        val downloadPodCastDataVO: DownloadPodCastDataVO = DownloadPodCastDataVO(
            podCast.UpNextId, podCast.UpNextTitle, podCast.UpNextDescription,
            podCast.UpNextAudio,podCast.UpNextAudio)

        mPodCastModel.saveDownloadPodcastItem(downloadPodCastDataVO,onSuccess = {},onError = {})
        mView?.showDownloadPodcastItem(podCast)
    }


    private fun getPreRequestPodCast(lifecycleOwner: LifecycleOwner) {
        mView?.showLoading()
          mPodCastModel.getPodCast(onError = {
              mView?.showErrorMessage(it)
          }).
              observe(lifecycleOwner, Observer {

                  it?.let {
                      mView?.showNowPlayingPodCast(it)
                  }
              })

          mPodCastModel.getAllUpNextPodCast (onError = {
              mView?.showErrorMessage(it)
          })
              .observe(lifecycleOwner, Observer {

                  it?.let {
                      mView?.hideLoading()
                      mView?.showUpNextPodCastlists(it) }
              })

    }



}