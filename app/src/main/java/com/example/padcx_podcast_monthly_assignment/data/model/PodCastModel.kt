package com.example.padcx_podcast_monthly_assignment.data.model

import androidx.lifecycle.LiveData
import com.example.padcx_podcast_monthly_assignment.data.vos.*
import io.reactivex.Observable

interface PodCastModel {

    fun getPodCastFromApiAndSaveToDatabase(onError :(String) -> Unit, onSuccess : () -> Unit)
    fun getPodCast(onError: (String) -> Unit) : LiveData<PodCastDataVO>


    fun getAllUpNextPodCastFromApiAndSaveToDatabase(onError :(String) -> Unit, onSuccess : () -> Unit)
    fun getAllUpNextPodCast(onError: (String) -> Unit) : LiveData<UpNextPodCastPlaylistsVO>


    fun getAllPodCastCategoryFromApiAndSaveToDatabase(onError :(String) -> Unit, onSuccess : () -> Unit)
    fun getAllPodCastCategory(onError: (String) -> Unit) : LiveData<List<PodCastCategoryVO>>


    fun getDownloadPodCast() : LiveData<List<DownloadPodCastDataVO>>

    fun getPodCastById(id : String) : Observable<DetailVO>
}