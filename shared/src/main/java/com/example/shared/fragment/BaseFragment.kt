package com.example.shared.fragment

import android.app.Dialog
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.shared.mvp.view.BaseView

abstract class BaseFragment : Fragment() {
    private var loadingView: Dialog? = null
//    override fun hideLoading() {
//        loadingView?.let {
//            if (it.isShowing) {
//                it.cancel()
//                loadingView = null
//            }
//        }
//    }
//
//    override fun showLoading() {
//        if (loadingView == null) {
//            loadingView = context?.let { Dialog(it) }
//        }
//        loadingView?.let {
//            val mDialogView = LayoutInflater.from(activity).inflate(R.layout.loading_layout_dialog, null)
//            it.setContentView(mDialogView)
//            it.setCancelable(false)
//            it.show()
//            it.window?.setBackgroundDrawable(null)
//        }
//    }
//
//    override fun getLifeCycleOwner(): LifecycleOwner =this
//
//    override fun showErrorMessage(errorMessage: String) {
//
//    }



}