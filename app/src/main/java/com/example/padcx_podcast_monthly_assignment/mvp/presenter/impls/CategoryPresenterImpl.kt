package com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.CategoryPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.view.CategoryView
import com.example.shared.mvp.presenter.AbstractBasePresenter

class CategoryPresenterImpl : CategoryPresenter ,AbstractBasePresenter<CategoryView>(){
    override fun onUIReady(lifecycleOwner: LifecycleOwner) {
    }
}