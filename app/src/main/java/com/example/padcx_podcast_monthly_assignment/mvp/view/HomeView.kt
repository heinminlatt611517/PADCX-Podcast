package com.example.padcx_podcast_monthly_assignment.mvp.view

import com.example.shared.mvp.view.BaseView

interface HomeView : BaseView {
    fun enableSwipeRefresh()
    fun disableSwipeRefresh()
}