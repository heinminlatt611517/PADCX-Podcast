package com.example.padcx_podcast_monthly_assignment.fragments

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.activities.MainActivity
import com.example.padcx_podcast_monthly_assignment.activities.PodcastDetailActivity
import com.example.padcx_podcast_monthly_assignment.adapter.UpNextPodcastAdapter
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastPlaylistsVO
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.HomePresenter
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls.HomePresenterImpl
import com.example.padcx_podcast_monthly_assignment.mvp.view.HomeView
import com.example.padcx_podcast_monthly_assignment.views.viewPods.FullPlayerViewPod
import com.example.shared.fragment.BaseFragment
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.custom_full_controller_view.*
import kotlinx.android.synthetic.main.fragment_home.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : BaseFragment(), HomeView {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mHomePresenter: HomePresenter
    private lateinit var mUpNextPodcastAdapter: UpNextPodcastAdapter
    private lateinit var mFullPlayerViewPod: FullPlayerViewPod
    private lateinit var mShimmerLayout : ShimmerFrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mShimmerLayout = view.findViewById(R.id.loading_layout)

        setUpPresenter()
        setUpRecyclerView()
        setUpViewPod()


        mHomePresenter.onUIReady(this)
    }



    private fun setUpViewPod() {
        mFullPlayerViewPod = fullPlayerViewPod as FullPlayerViewPod
    }

    private fun setUpPresenter() {
        mHomePresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mHomePresenter.initPresenter(this)
    }

    private fun setUpRecyclerView() {
        mUpNextPodcastAdapter = UpNextPodcastAdapter(mHomePresenter)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_upNext.layoutManager = linearLayoutManager
        rv_upNext.adapter = mUpNextPodcastAdapter

    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showLoading() {
        normal_layout.visibility = View.GONE
        loading_layout.visibility = View.VISIBLE
        mShimmerLayout.startShimmer()
    }

    override fun hideLoading() {
        normal_layout.visibility = View.VISIBLE
        loading_layout.visibility = View.GONE
        mShimmerLayout.stopShimmer()
    }


    override fun showNowPlayingPodCast(podCastEpisode: PodCastDataVO) {
        context?.let { mFullPlayerViewPod.setData(podCastEpisode, it) }
    }




    override fun showUpNextPodCastlists(podCastPlaylists: UpNextPodCastPlaylistsVO) {
        mUpNextPodcastAdapter.setNewData(podCastPlaylists.items.toMutableList())
    }

    override fun onTapPlayButton() {
        TODO("Not yet implemented")
    }

    override fun navigateToDetailScreen(id: String) {

        startActivity(context?.let { PodcastDetailActivity.newIntent(it,id) })
    }


    override fun showErrorMessage(errorMessage: String) {
        Log.d("Error", errorMessage)
    }


    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onDestroy() {
        mFullPlayerViewPod.onDestroy()
        super.onDestroy()
    }

    override fun onStop() {
        mFullPlayerViewPod.onDestroy()
        super.onStop()
    }

}