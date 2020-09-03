package com.example.padcx_podcast_monthly_assignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.data.vos.DetailVO
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.DetailPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls.DetailPresenterImpl
import com.example.padcx_podcast_monthly_assignment.mvp.view.DetailView
import com.example.padcx_podcast_monthly_assignment.views.viewPods.FullPlayerViewPod
import com.example.padcx_podcast_monthly_assignment.views.viewPods.MiniPlayerViewPod
import com.example.shared.activity.BaseActivity
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.activity_podcast_detail.*

class PodcastDetailActivity : BaseActivity(),DetailView {


    companion object{
        const val ID_EXTRA="Id Extra"
        fun newIntent(context: Context,id : String) : Intent{
            val intent=Intent(context,PodcastDetailActivity::class.java)
            intent.putExtra(ID_EXTRA,id)
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
        intent.getStringExtra(ID_EXTRA)?.let { mDetailPresenter.getPodCastEpisodeById(it) }
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
        Log.d("Detail",data.toString())
       mMiniPlayerViewPod.setData(data.audio)
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
        super.onDestroy()
    }

}