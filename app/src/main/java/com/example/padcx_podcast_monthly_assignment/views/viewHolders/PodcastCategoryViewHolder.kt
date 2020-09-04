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

        itemView.iv_category.glideImageLoader(itemView.context,
            "https://cdn-images-1.listennotes.com/podcasts/real-af-with-andy-frisella-andy-frisella-d_8pPgzeQW0-9-wONFLpkW3.300x300.jpg")
    }

}