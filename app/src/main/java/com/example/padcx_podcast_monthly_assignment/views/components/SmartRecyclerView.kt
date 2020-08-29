package com.example.padcx_podcast_monthly_assignment.views.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SmartRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var mEmptyView : View? = null


    private val dataObserver = object : AdapterDataObserver(){
        override fun onChanged() {
            super.onChanged()

            checkIfEmpty()
        }


        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)

            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {

            super.onItemRangeInserted(positionStart, itemCount)

            checkIfEmpty()

        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {

        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(dataObserver)
        super.setAdapter(adapter)

        adapter?.registerAdapterDataObserver(dataObserver)
        checkIfEmpty()


    }


    fun setEmptyView(emptyView : View){
        mEmptyView=emptyView
    }

    private fun checkIfEmpty(){
        val isEmpty = adapter!!.itemCount == 0

       if (mEmptyView != null){
           mEmptyView!!.visibility = if (isEmpty) View.VISIBLE else View.INVISIBLE
           visibility = if (isEmpty) View.INVISIBLE else View.VISIBLE
       }
    }

}