package com.example.padcx_podcast_monthly_assignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast_detail)

        setUpPresenter()
        setUpViewPod()

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

    }

    override fun hideLoading() {

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
        tv_detailEpisodeDescription.text=data.description

    }

    override fun showErrorMessage(errorMessage: String) {
        TODO("Not yet implemented")
    }

    override fun getLifeCycleOwner(): LifecycleOwner =this


    override fun onDestroy() {
        mMiniPlayerViewPod.onDestroy()
        super.onDestroy()
    }
}