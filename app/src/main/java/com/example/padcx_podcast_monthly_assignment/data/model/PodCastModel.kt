package com.example.padcx_podcast_monthly_assignment.data.model

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.padcx_podcast_monthly_assignment.data.vos.*
import com.example.padcx_podcast_monthly_assignment.network.FirebaseApi
import io.reactivex.Observable

interface PodCastModel {

    var mFirebaseApi : FirebaseApi

    fun getPodCastFromApiAndSaveToDatabase(onError :(String) -> Unit, onSuccess : () -> Unit)
    fun getPodCast(onError: (String) -> Unit) : LiveData<PodCastDataVO>


    fun getAllUpNextPodCastFromApiAndSaveToDatabase(onError :(String) -> Unit, onSuccess : () -> Unit)
    fun getAllUpNextPodCast(onError: (String) -> Unit) : LiveData<List<UpNextPodCastDataVO>>


    fun getAllPodCastCategoryFromApiAndSaveToDatabase(onError :(String) -> Unit, onSuccess : () -> Unit)
    fun getAllPodCastCategory(onError: (String) -> Unit) : LiveData<List<PodCastCategoryVO>>


    fun getDownloadPodCast() : LiveData<List<DownloadPodCastDataVO>>

    fun getPodCastById(id : String) : LiveData<UpNextPodCastDataVO>

    fun saveDownloadPodcastItem(donwloadVO: DownloadPodCastDataVO, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun startDownloadPodcast(context: Context, dataVO: UpNextPodCastDataVO)

    fun getAllDownloadPodcastList(onError: (String) -> Unit) : LiveData<List<DownloadPodCastDataVO>>


    fun getDetailEpisodeData(episodeId : String, onError: (String) -> Unit) : LiveData<DetailVO>
    fun getDetailFromApiAndSaveToDatabase(episodeId : String, onSuccess: (detailVO :DetailVO) -> Unit, onError: (String) -> Unit)


    fun getRandomPodCast(randomPodCast : (podCast : PodCastDataVO) -> Unit)


    fun deleteAllUpNextPodcast()

    fun getRandomPodCastFromDatabase() : LiveData<UpNextPodCastDataVO>


}