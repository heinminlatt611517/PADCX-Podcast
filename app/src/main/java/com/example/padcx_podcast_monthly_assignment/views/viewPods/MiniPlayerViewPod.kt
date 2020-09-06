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
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.custom_full_controller_view.view.*
import kotlinx.android.synthetic.main.custom_full_controller_view.view.exo_play
import kotlinx.android.synthetic.main.custom_full_controller_view.view.mediacontroller_progress
import kotlinx.android.synthetic.main.custom_mini_controller_view.view.*
import kotlinx.android.synthetic.main.custom_mini_controller_view.view.exo_ffwd
import kotlinx.android.synthetic.main.custom_mini_controller_view.view.exo_rew
import java.util.*
import com.google.android.exoplayer2.ExoPlayerFactory.newSimpleInstance as newSimpleInstance1


class MiniPlayerViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaSource: MediaSource
    private lateinit var dataSourceFactory: DefaultDataSourceFactory
    private var isPlaying = false

    private var seekPlayerProgress: SeekBar? = null
    var mCurrentPosition: Int = 0
    var firstTime: Boolean = false
    var currentPosition: Long = 0



    override fun invalidate() {
        super.invalidate()
        setUpListener()
    }

    private fun setUpListener() {

    }

    private fun releasePlayer() {
        simpleExoPlayer.playWhenReady = false
    }

    fun setData(audioUrl: String) {
        setUpExoPlayerListener(audioUrl)
    }

    fun onDestroy() {
        simpleExoPlayer.playWhenReady = false
    }

    fun onStop() {
        simpleExoPlayer.playWhenReady = false
    }

    fun setUpExoPlayerListener(audioUrl: String) {
        val userAgent: String = Util.getUserAgent(context, "exoPlayerSample")

        val httpDataSourceFactory = DefaultHttpDataSourceFactory(
            userAgent,
            DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
            DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
            true
        )

        simpleExoPlayer = newSimpleInstance1(context)

        dataSourceFactory = DefaultDataSourceFactory(context, null, httpDataSourceFactory)

        mediaSource =
            ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(audioUrl))



        with(simpleExoPlayer) {
            prepare(mediaSource)
            exo_play.setOnClickListener {
                setPlayPause(!isPlaying)
            }
        }

        exo_ffwd.setOnClickListener {
            if (!firstTime) {
                simpleExoPlayer.seekTo(30000)
                forWardSeekBar()
            }
            if (firstTime) {
                if (currentPosition < simpleExoPlayer.duration) {
                    currentPosition += 30000
                    simpleExoPlayer.seekTo(currentPosition)
                    forWardSeekBar()
                }
            }

        }


        exo_rew.setOnClickListener {

            if (currentPosition > 0) {
                currentPosition -= 15000
                simpleExoPlayer.seekTo(currentPosition)
                backWardSeekBar()
            }
        }

        initSeekBar()
    }



    private fun DefaultRenderersFactory(miniPlayerViewPod: MiniPlayerViewPod, nothing: Nothing?, extensionRendererModeOff: Int): DefaultRenderersFactory {
          return DefaultRenderersFactory(miniPlayerViewPod,nothing,extensionRendererModeOff)
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

    private fun forWardSeekBar() {
        Log.d("currentPosition", simpleExoPlayer.currentPosition.toString())
        seekPlayerProgress!!.progress = simpleExoPlayer.currentPosition.toInt() / 1000
        firstTime = true
    }

    private fun backWardSeekBar() {
        Log.d("currentBackWardPosition", simpleExoPlayer.currentPosition.toString())
        seekPlayerProgress!!.progress = simpleExoPlayer.currentPosition.toInt() / 1000
        firstTime = true
    }


    private fun setProgress() {

        var handler: Handler? = null

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
                    tv_currentTime.text = simpleExoPlayer.currentPosition.toInt().stringForTime(
                        Formatter()
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