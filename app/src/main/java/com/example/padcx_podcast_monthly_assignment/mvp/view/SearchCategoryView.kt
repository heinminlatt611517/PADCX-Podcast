package com.example.padcx_podcast_monthly_assignment.mvp.view

import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.shared.mvp.view.BaseView

interface SearchCategoryView : BaseView{
    fun showLoading()
    fun hideLoading()
    fun displayPodCastCategoryLists(categoryLists : ArrayList<PodCastCategoryVO>)
}