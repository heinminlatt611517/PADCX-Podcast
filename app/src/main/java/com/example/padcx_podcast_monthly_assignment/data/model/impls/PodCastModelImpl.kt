package com.example.padcx_podcast_monthly_assignment.data.model.impls

import com.example.padcx_podcast_monthly_assignment.BuildConfig
import com.example.padcx_podcast_monthly_assignment.data.model.BaseModel
import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.vos.DetailVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastPlaylistsVO
import io.reactivex.Observable

object PodCastModelImpl : BaseModel(),PodCastModel {

    override fun getRandomPodCastEpisode(): Observable<PodCastDataVO> {
      return  mPodCastApi.getRandomPodCastEpisode(BuildConfig.API_KEY)
    }

    override fun getPodCastPlaylists(): Observable<UpNextPodCastPlaylistsVO> {
        return mPodCastApi.getPodCastPlaylists(BuildConfig.PLAYLIST_ID,"episode_list","0","recent_added_first",BuildConfig.API_KEY)

    }

    override fun getPodCastCategory(): Observable<ArrayList<PodCastCategoryVO>> {
        return mPodCastApi.getPodCastCategory(0,BuildConfig.API_KEY)
            .map { it.genres }
    }

    override fun getPodCastById(id: String) : Observable<DetailVO> {
        return mPodCastApi.getEpisodeDetail(id,BuildConfig.API_KEY)
    }


}