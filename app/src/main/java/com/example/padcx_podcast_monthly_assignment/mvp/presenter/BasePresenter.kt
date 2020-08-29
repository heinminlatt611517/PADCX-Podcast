package com.example.padcx_podcast_monthly_assignment.mvp.presenter

import com.example.padcx_podcast_monthly_assignment.mvp.view.BaseView

interface BasePresenter<T : BaseView> {
    fun initPresenter(view : T)
}