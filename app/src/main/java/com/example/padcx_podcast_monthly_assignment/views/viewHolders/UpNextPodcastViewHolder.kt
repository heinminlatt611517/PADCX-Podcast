package com.example.padcx_podcast_monthly_assignment.views.viewHolders

import android.view.View
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.delegate.PodCastItemDelegate
import com.example.padcx_podcast_monthly_assignment.utils.glideImageLoader
import com.example.shared.viewHolder.BaseViewHolder
import kotlinx.android.synthetic.main.item_up_next_podcast.view.*

class UpNextPodcastViewHolder(private val mDelegate: PodCastItemDelegate,itemView: View) : BaseViewHolder<UpNextPodCastDataVO>(itemView) {
    override fun clickItem(it: View?) {
        mData?.id?.let { it1 -> mDelegate.onTapUpNextPodCastItem(it1) }
//        mData?.description?.let { it1 ->
//            mData?.audio?.let { it2 ->
//                mData?.title?.let { it3 ->
//                    mData?.image?.let { it4 ->
//                        mDelegate.onTapUpNextPodCastItem(
//                            it2, it3, it4,
//                            it1
//                        )
//                    }
//                }
//            }
//        }

    }


    override fun bindData(data: UpNextPodCastDataVO) {
       mData=data

        data.image.let {
            if (it != null) {
                itemView.iv_upNext.glideImageLoader(itemView.context, it)
            }
        }

//        itemView.tv_episodeDescriptionTitle.text=data.data?.UpNextTitle
//        itemView.progressBar.progress = data.data?.UpNextAudioLengthSecs!! / 60 % 60
//
//        itemView.tv_remainPlayHour.text = stringForTime(data.data?.UpNextAudioLengthSecs!!)
//
//        itemView.iv_download.setOnClickListener {
//            mData!!.data?.let { it1 -> mDelegate.onTapDownloadButton(it1) }
//        }

        itemView.tv_episodeDescriptionTitle.text=data.title
        itemView.progressBar.progress = data.audio_length_sec!! / 60 % 60

        itemView.tv_remainPlayHour.text = stringForTime(data?.audio_length_sec!!)

        itemView.iv_download.setOnClickListener {
            mData!!.let { it1 -> mDelegate.onTapDownloadButton(it1) }
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

