package com.example.padcx_podcast_monthly_assignment.data.model

import com.example.padcx_podcast_monthly_assignment.data.vos.DetailVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastPlaylistsVO
import io.reactivex.Observable

interface PodCastModel {
    fun getRandomPodCastEpisode() : Observable<PodCastDataVO>

    fun getPodCastPlaylists() : Observable<UpNextPodCastPlaylistsVO>

    fun getPodCastCategory() : Observable<ArrayList<PodCastCategoryVO>>

    fun getPodCastById(id : String) : Observable<DetailVO>
}