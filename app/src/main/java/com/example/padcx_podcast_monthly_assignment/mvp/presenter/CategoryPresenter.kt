package com.example.padcx_podcast_monthly_assignment.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.mvp.view.CategoryView
import com.example.shared.mvp.presenter.BasePresenter

interface CategoryPresenter : BasePresenter<CategoryView> {
    fun onUIReady(lifecycleOwner: LifecycleOwner)

}