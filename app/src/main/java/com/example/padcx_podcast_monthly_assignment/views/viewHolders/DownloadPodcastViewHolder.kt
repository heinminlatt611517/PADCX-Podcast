package com.example.padcx_podcast_monthly_assignment.views.viewHolders

import android.text.Html
import android.view.View
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.data.vos.DownloadPodCastDataVO
import com.example.shared.viewHolder.BaseViewHolder
import kotlinx.android.synthetic.main.custom_full_controller_view.view.*
import kotlinx.android.synthetic.main.custom_full_controller_view.view.tv_episodeDescription
import kotlinx.android.synthetic.main.item_download_podcast.view.*
import kotlinx.android.synthetic.main.item_up_next_podcast.view.*
import kotlinx.android.synthetic.main.item_up_next_podcast.view.iv_download

class DownloadPodcastViewHolder(itemView: View,private val delegate : DownloadDelegate) : BaseViewHolder<DownloadPodCastDataVO>(itemView) {
    override fun clickItem(it: View?) {
        mData?.let { it1 ->
            delegate.onTapDownloadPodCastItem(it1)
        }
    }

    override fun bindData(data: DownloadPodCastDataVO) {
       mData = data

        Glide.with(itemView.context)
            .load(data.download_podcast_url)
            .into(itemView.iv_Download)

        itemView.tv_downloadEpisodeDescriptionTitle.text=data.donwload_podcast_title
        itemView.tv_episodeDescription.text = Html.fromHtml(data.download_podcast_description)
    }

    interface DownloadDelegate{
        fun onTapDownloadPodCastItem(podCastDataVO: DownloadPodCastDataVO)
    }

}