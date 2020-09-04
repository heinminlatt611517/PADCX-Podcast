package com.example.padcx_podcast_monthly_assignment.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.padcx_podcast_monthly_assignment.R
import com.example.padcx_podcast_monthly_assignment.data.vos.DownloadPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.presistence.db.PodcastDb
import com.example.padcx_podcast_monthly_assignment.utils.glideImageLoader
import com.example.shared.fragment.BaseFragment
import kotlinx.android.synthetic.main.activity_podcast_detail.*
import kotlinx.android.synthetic.main.fragment_account.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AccountFragment : BaseFragment(){

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mDatabase: PodcastDb


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
        return inflater.inflate(R.layout.fragment_account, container, false)


    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDatabase = activity?.let { PodcastDb.getDbInstance(it) }!!

        btn_click.setOnClickListener {

            var mDownloadPodCast = DownloadPodCastDataVO()
            mDownloadPodCast.DownloadAudio = "dsdfkalf"
            mDownloadPodCast.DownloadAudioLengthSecs = 0
            mDownloadPodCast.DownloadDescription = ""
            mDownloadPodCast.DownloadExplicitContent = false
            mDownloadPodCast.DownloadId = "aassssddd"
            mDownloadPodCast.DownloadImage = ""
            mDownloadPodCast.DownloadLink = ""
            mDownloadPodCast.DownloadListennotesEditUrl = ""
            mDownloadPodCast.DownloadListennotesUrl =""
            mDownloadPodCast.DownloadMaybeAudioInvarid = false
            mDownloadPodCast.DownloadPubDateMs = 0
            mDownloadPodCast.DownloadThumbnail =""
            mDownloadPodCast.DownloadTitle = ""

            mDatabase.podcastDao().insertDownloadPodCast(mDownloadPodCast)
        }


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}