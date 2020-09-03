package com.example.padcx_podcast_monthly_assignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.activities.PodcastDetailActivity
import com.example.padcx_podcast_monthly_assignment.adapter.DownloadPodcastAdapter
import com.example.padcx_podcast_monthly_assignment.data.vos.DownloadPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.DownloadPodcastPresenter
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls.DownloadPodcastPresenterImpl
import com.example.padcx_podcast_monthly_assignment.mvp.view.DownloadPodcastView
import com.example.padcx_podcast_monthly_assignment.views.viewPods.EmptyViewPod
import com.example.shared.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_download.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DownloadFragment : BaseFragment(), DownloadPodcastView {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mDownloadPodCastAdapter: DownloadPodcastAdapter
    private lateinit var mDownloadPodCastPresenter: DownloadPodcastPresenter
    private lateinit var mViewPodEmpty: EmptyViewPod

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

        return inflater.inflate(R.layout.fragment_download, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpEmptyView()
        setUpRecyclerView()

        mDownloadPodCastPresenter.onUIReady(this)
    }

    private fun setUpEmptyView() {
        mViewPodEmpty = view_pod_empty as EmptyViewPod
        mViewPodEmpty.setDelegate(mDownloadPodCastPresenter)

    }

    private fun setUpPresenter() {
        mDownloadPodCastPresenter =
            ViewModelProviders.of(this).get(DownloadPodcastPresenterImpl::class.java)
        mDownloadPodCastPresenter.initPresenter(this)
    }


    private fun setUpRecyclerView() {
        mDownloadPodCastAdapter = DownloadPodcastAdapter(mDownloadPodCastPresenter)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_downloadPodCast.layoutManager = linearLayoutManager
        rv_downloadPodCast.adapter = mDownloadPodCastAdapter


        rv_downloadPodCast.setEmptyView(mViewPodEmpty)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DownloadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun showDownloadPodCast(downloadPodCastList: List<DownloadPodCastDataVO>) {
        mDownloadPodCastAdapter.setNewData(downloadPodCastList.toMutableList())
    }

    override fun navigateToPodCastDetailScreen(id: String) {
        startActivity(context?.let { PodcastDetailActivity.newIntent(it, id) })
    }

    override fun showErrorMessage(errorMessage: String) {
        showSnackbar(errorMessage)
    }


    override fun getLifeCycleOwner(): LifecycleOwner = this


}