package com.example.padcx_podcast_monthly_assignment.views.viewPods

import android.R
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout
import android.widget.SeekBar
import com.example.padcx_podcast_monthly_assignment.utils.stringForTime
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.custom_full_controller_view.view.exo_play
import kotlinx.android.synthetic.main.custom_full_controller_view.view.mediacontroller_progress
import kotlinx.android.synthetic.main.custom_mini_controller_view.view.*
import java.util.*

class MiniPlayerViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaSource: MediaSource
    private lateinit var dataSourceFactory: DefaultDataSourceFactory
    private var isPlaying = false
    private var seekPlayerProgress: SeekBar? = null

    override fun invalidate() {
        super.invalidate()
        releasePlayer()
    }

    private fun releasePlayer() {
        simpleExoPlayer.playWhenReady = false
    }

    fun setData(audioUrl : String){
        setUpExoPlayerListener(audioUrl)
    }

    fun onDestroy(){
        simpleExoPlayer.playWhenReady = false
    }

    fun onStop(){
        simpleExoPlayer.playWhenReady = false
    }

    fun setUpExoPlayerListener(audioUrl: String) {
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context)
        dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "exoPlayerSample"))
        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(audioUrl))



        with(simpleExoPlayer) {
            prepare(mediaSource)
            exo_play.setOnClickListener {
                setPlayPause(!isPlaying)
            }
        }

        initSeekBar()
    }


    private fun setPlayPause(playing: Boolean) {

        isPlaying = playing
        simpleExoPlayer.playWhenReady = playing
        if (!isPlaying) {
            exo_play.setImageResource(R.drawable.ic_media_play)
        } else {
            setProgress()
            exo_play.setImageResource(R.drawable.ic_media_pause)
        }

    }

    private fun setProgress() {

        var handler : Handler? = null

        Log.d("duration", simpleExoPlayer.duration.toString())
        seekPlayerProgress!!.progress = 0
        seekPlayerProgress!!.max = simpleExoPlayer.duration.toInt()
        tv_currentTime.text = simpleExoPlayer.currentPosition.toInt().stringForTime(Formatter())
        tv_endTime.text = simpleExoPlayer.duration.toInt().stringForTime(Formatter())
        if (handler == null) {
            handler = Handler()
        }
        //Make sure you update Seekbar on UI thread
        handler.post(object : Runnable {
            override fun run() {
                if (simpleExoPlayer != null && isPlaying) {
                    seekPlayerProgress!!.max = simpleExoPlayer.duration.toInt() / 1000
                    val mCurrentPosition = simpleExoPlayer.currentPosition.toInt() / 1000
                    seekPlayerProgress!!.progress = mCurrentPosition
                    tv_currentTime.text = simpleExoPlayer.currentPosition.toInt().stringForTime(Formatter()
                    )
                    tv_endTime.text = simpleExoPlayer.duration.toInt().stringForTime(Formatter())
                    handler.postDelayed(this, 1000)
                }
            }
        })
    }

    private fun initSeekBar() {
        seekPlayerProgress = mediacontroller_progress as SeekBar
        seekPlayerProgress!!.requestFocus()
        seekPlayerProgress!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                if (!fromUser) {
                    // We're not interested in programmatically generated changes to
                    // the progress bar's position.
                    return
                }
                simpleExoPlayer.seekTo(progress * 1000.toLong())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        seekPlayerProgress!!.max = 0
        seekPlayerProgress!!.max = simpleExoPlayer.duration.toInt() / 1000
    }


}