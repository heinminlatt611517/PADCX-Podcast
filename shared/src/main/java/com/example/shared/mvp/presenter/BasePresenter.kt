package com.example.shared.mvp.presenter

import com.example.shared.mvp.view.BaseView


interface BasePresenter<T : BaseView> {
    fun initPresenter(view : T)
}