package com.example.padcx_podcast_monthly_assignment.mvp.presenter

import androidx.lifecycle.ViewModel
import com.example.padcx_podcast_monthly_assignment.mvp.view.BaseView

abstract class AbstractBasePresenter<T : BaseView> : BasePresenter<T> , ViewModel(){
    var mView : T? = null

    override fun initPresenter(view: T) {
        mView = view
    }
}