package com.example.padcx_podcast_monthly_assignment.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.mvp.view.SearchCategoryView
import com.example.shared.mvp.presenter.BasePresenter

interface SearchCategoryPresenter : BasePresenter<SearchCategoryView> {
    fun onUIReady(lifecycleOwner: LifecycleOwner)

}