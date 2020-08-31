package com.example.padcx_podcast_monthly_assignment.views.viewHolders

import android.view.View
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.data.vos.ItemVO
import com.example.padcx_podcast_monthly_assignment.delegate.PodCastItemDelegate
import com.example.shared.viewHolder.BaseViewHolder
import kotlinx.android.synthetic.main.item_up_next_podcast.view.*

class UpNextPodcastViewHolder(private val mDelegate: PodCastItemDelegate,itemView: View) : BaseViewHolder<ItemVO>(itemView) {
    override fun clickItem(it: View?) {
        mData?.data?.id?.let { it1 -> mDelegate.onTapUpNextPodCastItem(it1) }
    }

    override fun bindData(data: ItemVO) {
       mData=data

        Glide.with(itemView.context)
            .load(data.data.image)
            .into(itemView.iv_upNext)

        itemView.tv_episodeDescriptionTitle.text=data.data.title

    }

}