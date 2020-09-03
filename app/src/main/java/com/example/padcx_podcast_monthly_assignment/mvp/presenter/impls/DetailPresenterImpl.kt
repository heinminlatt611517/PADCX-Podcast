package com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls

import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.model.impls.PodCastModelImpl
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.DetailPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.view.DetailView
import com.example.padcx_podcast_monthly_assignment.utils.EM_NO_INTERNET_CONNECTION
import com.example.shared.mvp.presenter.AbstractBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenterImpl : DetailPresenter,AbstractBasePresenter<DetailView>() {

    private val mPodCastModel : PodCastModel = PodCastModelImpl

    override fun getPodCastEpisodeById(id: String) {
        mView?.showLoading()
        mPodCastModel.getPodCastById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mView?.hideLoading()
                mView?.displayPodCastEpisode(it)
            },{
                mView?.showErrorMessage(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
            })

    }
}