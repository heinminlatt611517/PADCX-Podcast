package com.example.padcx_podcast_monthly_assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.delegate.PodCastItemDelegate
import com.example.shared.viewHolder.BaseViewHolder
import com.example.padcx_podcast_monthly_assignment.views.viewHolders.UpNextPodcastViewHolder
import com.example.shared.adapter.BaseRecyclerAdapter

class UpNextPodcastAdapter(delegate : PodCastItemDelegate) : BaseRecyclerAdapter<BaseViewHolder<UpNextPodCastDataVO>, UpNextPodCastDataVO>() {

    val mDelegate = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UpNextPodCastDataVO> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_up_next_podcast, parent, false)
        return UpNextPodcastViewHolder(mDelegate,view)
    }
}