package com.example.padcx_podcast_monthly_assignment.views.viewHolders

import android.view.View
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.padcx_podcast_monthly_assignment.utils.glideImageLoader
import com.example.shared.viewHolder.BaseViewHolder
import kotlinx.android.synthetic.main.item_podcast_category.view.*

class PodcastCategoryViewHolder(itemView: View) : BaseViewHolder<PodCastCategoryVO>(itemView) {
    override fun clickItem(it: View?) {

    }

    override fun bindData(data: PodCastCategoryVO) {
         mData = data
        //data.image_url?.let { itemView.iv_category.glideImageLoader(itemView.context, it) }

    }

}