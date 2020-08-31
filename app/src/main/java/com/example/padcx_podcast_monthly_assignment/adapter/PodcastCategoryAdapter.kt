package com.example.padcx_podcast_monthly_assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.shared.viewHolder.BaseViewHolder
import com.example.padcx_podcast_monthly_assignment.views.viewHolders.PodcastCategoryViewHolder
import com.example.shared.adapter.BaseRecyclerAdapter

class PodcastCategoryAdapter : BaseRecyclerAdapter<BaseViewHolder<PodCastCategoryVO>, PodCastCategoryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PodCastCategoryVO> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_podcast_category, parent, false)
        return PodcastCategoryViewHolder(view)
    }
}