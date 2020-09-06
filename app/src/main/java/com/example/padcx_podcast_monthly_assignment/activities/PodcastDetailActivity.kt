package com.example.padcx_podcast_monthly_assignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.text.Html
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.data.vos.DetailVO
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.DetailPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls.DetailPresenterImpl
import com.example.padcx_podcast_monthly_assignment.mvp.view.DetailView
import com.example.padcx_podcast_monthly_assignment.utils.DOWNLOAD_FRAGMENT
import com.example.padcx_podcast_monthly_assignment.views.viewPods.MiniPlayerViewPod
import com.example.shared.activity.BaseActivity
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.RenderersFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import kotlinx.android.synthetic.main.activity_podcast_detail.*


class PodcastDetailActivity : BaseActivity(),DetailView {


    companion object{
        const val PAGE_EXTRA = "page_extra"
        const val AUDIO_FILE_PATH_EXTRA = "audio_file_path_extra"
        const val ID_EXTRA="Id Extra"
        fun newIntent(context: Context,id : String,page : String,audioUrl : String) : Intent{
            val intent=Intent(context,PodcastDetailActivity::class.java)
            intent.putExtra(ID_EXTRA,id)
            intent.putExtra(PAGE_EXTRA,page)
            intent.putExtra(AUDIO_FILE_PATH_EXTRA,audioUrl)

            return intent
        }
    }


    private lateinit var mDetailPresenter : DetailPresenter
    private lateinit var mMiniPlayerViewPod: MiniPlayerViewPod
    private lateinit var mShimmerLayout : ShimmerFrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_podcast_detail)

        setUpPresenter()
        setUpViewPod()
        mShimmerLayout = findViewById(R.id.loading_layout)
        //intent.getStringExtra(ID_EXTRA)?.let { mDetailPresenter.getPodCastEpisodeById(it) }
        mDetailPresenter.getPodCastEpisodeById("")
    }

    private fun setUpViewPod() {
        mMiniPlayerViewPod = miniPlayerViewPod as MiniPlayerViewPod
    }

    private fun setUpPresenter() {
        mDetailPresenter=ViewModelProviders.of(this).get(DetailPresenterImpl::class.java)
        mDetailPresenter.initPresenter(this)
    }

    override fun showLoading() {
      normal_layout.visibility = View.GONE
        loading_layout.visibility=View.VISIBLE
        mShimmerLayout.startShimmer()
    }

    override fun hideLoading() {
        normal_layout.visibility = View.VISIBLE
        loading_layout.visibility=View.GONE
        mShimmerLayout.stopShimmer()
    }

    override fun displayPodCastEpisode(data: DetailVO) {
        Log.d("DetailAudio",data.audio)
        if (intent.getStringExtra(PAGE_EXTRA).toString() == DOWNLOAD_FRAGMENT)
        {
            Log.d("Audio","download audio")
            var audioUrl = intent.getStringExtra(AUDIO_FILE_PATH_EXTRA).toString()
            mMiniPlayerViewPod.setData(Environment.getExternalStorageDirectory().absolutePath +"/Download/.mp3")
        }
       else{
            Log.d("Audio","home audio")
            mMiniPlayerViewPod.setData(data.audio)
        }

        bindData(data)
    }

    private fun bindData(data: DetailVO) {
       Glide.with(this)
           .load(data.image)
           .into(iv_podCastDetail)

        tv_EpisodeTitle.text=data.title
        tv_detailEpisodeDescription.text=Html.fromHtml(data.description)

    }

    override fun showErrorMessage(errorMessage: String) {
       showSnackbar(errorMessage)
    }

    override fun getLifeCycleOwner(): LifecycleOwner =this


    override fun onDestroy() {
        mMiniPlayerViewPod.onDestroy()
        super.onDestroy()
    }

    private fun callOfflineMode(){
        val renderersFactory: RenderersFactory =
            DefaultRenderersFactory(this, null, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF)
        val trackSelector: TrackSelector = DefaultTrackSelector()

    }

}