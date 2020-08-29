package com.example.padcx_podcast_monthly_assignment.views.viewPods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_pod_empty.view.*

class EmptyViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {


    var mDelegate : Delegate? =null

    fun setDelegate(delegate : Delegate){
        mDelegate=delegate
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpListener()
    }

    private fun setUpListener() {
        btn_findSomething.setOnClickListener {
            mDelegate?.onTapFindSomethingNew()
        }

        btn_reload.setOnClickListener {
            mDelegate?.onTapReload()
        }
    }


    interface Delegate{
        fun onTapFindSomethingNew()
        fun onTapReload()
    }

}