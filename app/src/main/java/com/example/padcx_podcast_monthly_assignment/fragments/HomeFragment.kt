package com.example.padcx_podcast_monthly_assignment.fragments

import android.app.Activity
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.activities.PodcastDetailActivity
import com.example.padcx_podcast_monthly_assignment.adapter.UpNextPodcastAdapter
import com.example.padcx_podcast_monthly_assignment.data.vos.DownloadPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastPlaylistsVO
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.HomePresenter
import com.example.padcx_podcast_monthly_assignment.mvp.presenter.impls.HomePresenterImpl
import com.example.padcx_podcast_monthly_assignment.mvp.view.HomeView
import com.example.padcx_podcast_monthly_assignment.presistence.db.PodcastDb
import com.example.padcx_podcast_monthly_assignment.utils.HOME_FRAGMENT
import com.example.padcx_podcast_monthly_assignment.views.viewPods.FullPlayerViewPod
import com.example.shared.fragment.BaseFragment
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.android.synthetic.main.fragment_home.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : BaseFragment(), HomeView {

    private var param1: String? = null
    private var param2: String? = null
    private val PERMISSION_REQUEST_CODE = 101

    private lateinit var mHomePresenter: HomePresenter
    private lateinit var mUpNextPodcastAdapter: UpNextPodcastAdapter
    private lateinit var mFullPlayerViewPod: FullPlayerViewPod
    private lateinit var mShimmerLayout: ShimmerFrameLayout
    private lateinit var mDatabase: PodcastDb


    private var downloadManager: DownloadManager? = null

    private var downloadID: Long = 0

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
        mDatabase = activity?.let { PodcastDb.getDbInstance(it) }!!

        setUpPresenter()
        setUpRecyclerView()
        setUpViewPod()
        mShimmerLayout = view.findViewById(R.id.loading_layout)
        mHomePresenter.onUIReady(this)


        val filter =
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        context?.registerReceiver(onDownloadComplete, filter)

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
        Log.d("podCastEpisode", podCastEpisode.toString())
    }


    override fun showUpNextPodCastlists(podCastPlaylists: UpNextPodCastPlaylistsVO) {
        mUpNextPodcastAdapter.setNewData(podCastPlaylists.items.toMutableList())
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTapDownloadButton(podCastData: UpNextPodCastDataVO) {
        //beginDownload(podCastData)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun beginDownload(podCastData: UpNextPodCastDataVO) {

        Toast.makeText(context, "Downloading....", Toast.LENGTH_SHORT).show()

        downloadManager = activity?.getSystemService(DOWNLOAD_SERVICE) as DownloadManager?
        val Download_Uri =
            Uri.parse(podCastData.UpNextAudio)
        val request =
            DownloadManager.Request(Download_Uri)

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setAllowedOverRoaming(false)
        request.setTitle("My Data Download")
        request.setDescription("Android Data download using DownloadManager.")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "/PodCastAudio/audio.mp3"
            )

        downloadID = downloadManager!!.enqueue(request)


    }

    override fun navigateToDetailScreen(id: String) {

        startActivity(PodcastDetailActivity.newIntent(activity as Context, id, HOME_FRAGMENT,""))
    }

    override fun showDownloadPodcastItem(data: UpNextPodCastDataVO) {
        setUpPremission(data)
    }

    private fun setUpPremission(data: UpNextPodCastDataVO) {
        val permission = ContextCompat.checkSelfPermission(
            activity as Activity,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        } else {
            data?.let {  mHomePresenter?.onDownloadItem(activity as Context,it) }
        }
    }


    override fun showErrorMessage(errorMessage: String) {
        showSnackbar(errorMessage)
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }


    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(onDownloadComplete)
    }



    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            activity as Activity,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }


    private var onDownloadComplete: BroadcastReceiver? =
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (downloadID === id) {
                    Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(activity as Activity, "Permission Denied", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "Permission GRANDED", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}