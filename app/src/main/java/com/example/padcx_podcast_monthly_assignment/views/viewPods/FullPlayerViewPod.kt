package com.example.padcx_podcast_monthly_assignment.views.viewPods

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.custom_full_controller_view.view.*
import kotlinx.android.synthetic.main.item_up_next_podcast.view.*

class FullPlayerViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mData : PodCastDataVO? = null
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaSource: MediaSource
    private lateinit var dataSourceFactory: DefaultDataSourceFactory


    fun setData(data : PodCastDataVO,context: Context){
        mData = data
        Glide.with(context)
            .load(data.image)
            .into(iv_fullPlay)

        tv_fullPlayTitle.text=data.title
        tv_episodeDescription.text=data.podcast.toString()

        setUpExoPlayerListener()

    }

    fun onDestroy(){
        simpleExoPlayer.playWhenReady = false
    }

    private fun setUpExoPlayerListener() {
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context)
        dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "exoPlayerSample"))
        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mData?.audio.toString()))

        with(simpleExoPlayer) {
            prepare(mediaSource)
            exo_play.setOnClickListener {
                playWhenReady = true
            }
        }
    }


    interface Delegate{
        fun onDestroy()
    }



}