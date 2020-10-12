package com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.model.impls.PodCastModelImpl
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.SearchCategoryPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.view.SearchCategoryView
import com.example.shared.mvp.presenter.AbstractBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchCategoryPresenterImpl : SearchCategoryPresenter ,AbstractBasePresenter<SearchCategoryView>(){

    private val mPodCastModel : PodCastModel = PodCastModelImpl

    override fun onUIReady(lifecycleOwner: LifecycleOwner) {
        getPodCastCategory(lifecycleOwner)

    }

    private fun getPodCastCategory(lifecycleOwner: LifecycleOwner) {
        mView?.showLoading()
        mPodCastModel.getAllPodCastCategory(onError = {
            mView?.hideLoading()
            mView?.showErrorMessage(it)
        }).observe(lifecycleOwner, Observer {
            mView?.hideLoading()
            it?.let { mView?.displayPodCastCategoryLists(it as ArrayList) }
        })
    }
}