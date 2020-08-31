package com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.model.impls.PodCastModelImpl
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.HomePresenter
import com.example.padcx_podcast_monthly_assignment.mvp.view.HomeView
import com.example.shared.mvp.presenter.AbstractBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenterImpl : HomePresenter,AbstractBasePresenter<HomeView>() {

    private val mPodCastModel : PodCastModel = PodCastModelImpl

    override fun onUIReady(lifecycleOwner: LifecycleOwner) {
          getPreRequestPodCast()
    }

    override fun onTapUpNextPodCastItem(id: String) {
        mView?.navigateToDetailScreen(id)
    }


    private fun getPreRequestPodCast() {
        mView?.showLoading()
        mPodCastModel.getRandomPodCastEpisode()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mView?.showNowPlayingPodCast(it)
            },{
                mView?.showErrorMessage(it.localizedMessage)
            }
            )
            mPodCastModel.getPodCastPlaylists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView?.showUpNextPodCastlists(it)
                },{
                    mView?.showErrorMessage(it.localizedMessage)
                }
                )
      mView?.hideLoading()
    }



}