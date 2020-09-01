package com.example.padcx_podcast_monthly_assignment.views.viewHolders

import android.view.View
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.data.vos.ItemVO
import com.example.padcx_podcast_monthly_assignment.delegate.PodCastItemDelegate
import com.example.shared.viewHolder.BaseViewHolder
import kotlinx.android.synthetic.main.item_up_next_podcast.view.*
import java.util.*

class UpNextPodcastViewHolder(private val mDelegate: PodCastItemDelegate,itemView: View) : BaseViewHolder<ItemVO>(itemView) {
    override fun clickItem(it: View?) {
        mData?.data?.UpNextId?.let { it1 -> mDelegate.onTapUpNextPodCastItem(it1) }

    }


    override fun bindData(data: ItemVO) {
       mData=data

        Glide.with(itemView.context)
            .load(data.data?.UpNextImage)
            .into(itemView.iv_upNext)

        itemView.tv_episodeDescriptionTitle.text=data.data?.UpNextTitle
        itemView.progressBar.progress = data.data?.UpNextAudioLengthSecs!! / 60 % 60

        itemView.tv_remainPlayHour.text = stringForTime(data.data?.UpNextAudioLengthSecs!!)

        itemView.iv_download.setOnClickListener {
            data.data?.UpNextId?.let { it1 -> mDelegate.onTapDownloadButton(it1) }
        }

    }


    private fun stringForTime(timeMs: Int): String? {

        val seconds = timeMs % 60
        val minutes = timeMs / 60 % 60
        val hours = timeMs / 3600
        return if (minutes > 60) {
             hours.toString()+"hour"
        } else {
            minutes.toString()+"min"
        }
    }


}

