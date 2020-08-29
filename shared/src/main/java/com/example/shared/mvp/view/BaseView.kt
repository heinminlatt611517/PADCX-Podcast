package com.example.shared.mvp.view

import androidx.lifecycle.LifecycleOwner

interface BaseView {
    fun showErrorMessage(errorMessage : String)
    fun getLifeCycleOwner() : LifecycleOwner
}