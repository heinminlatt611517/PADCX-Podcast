package com.example.padcx_podcast_monthly_assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.padcx_podcast_monthly_assignment.R
import com.example.shared.viewHolder.BaseViewHolder
import com.example.padcx_podcast_monthly_assignment.views.viewHolders.DownloadPodcastViewHolder
import com.example.shared.adapter.BaseRecyclerAdapter

class DownloadPodcastAdapter : BaseRecyclerAdapter<BaseViewHolder<Int>, Int>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Int> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_download_podcast, parent, false)
        return DownloadPodcastViewHolder(view)
    }
}