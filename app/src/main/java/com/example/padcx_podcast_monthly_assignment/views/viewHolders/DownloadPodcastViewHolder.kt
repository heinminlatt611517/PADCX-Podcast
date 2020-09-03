package com.example.padcx_podcast_monthly_assignment.views.viewHolders

import android.view.View
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.data.vos.DownloadPodCastDataVO
import com.example.shared.viewHolder.BaseViewHolder
import kotlinx.android.synthetic.main.item_download_podcast.view.*
import kotlinx.android.synthetic.main.item_up_next_podcast.view.*

class DownloadPodcastViewHolder(itemView: View,private val delegate : DownloadDelegate) : BaseViewHolder<DownloadPodCastDataVO>(itemView) {
    override fun clickItem(it: View?) {
        mData?.DownloadId?.let { it1 -> delegate.onTapDownloadPodCastItem(it1) }
    }

    override fun bindData(data: DownloadPodCastDataVO) {
       mData = data

//        Glide.with(itemView.context)
//            .load(data.DownloadImage)
//            .into(itemView.iv_upNext)
//
//        itemView.tv_episodeDescriptionTitle.text=data.DownloadTitle
//        itemView.tv_episodeDescription.text = data.DownloadDescription
    }

    interface DownloadDelegate{
        fun onTapDownloadPodCastItem(id : String)
    }

}