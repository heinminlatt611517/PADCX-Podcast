package com.example.padcx_podcast_monthly_assignment.network

import com.example.padcx_podcast_monthly_assignment.data.vos.DetailVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastPlaylistsVO
import com.example.padcx_podcast_monthly_assignment.network.responses.PodCastCategoryResponse
import com.example.padcx_podcast_monthly_assignment.utils.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PodcastApi {

    @GET(GET_RANDOM_PODCAST_EPISODE)
    fun getRandomPodCastEpisode(@Header(PARAM_API_KEY) api_key : String) : Observable<PodCastDataVO>

    @GET("$GET_PODCAST_PLAYLISTS/{PLAYLIST_ID}")
    fun getPodCastPlaylists(@Path("PLAYLIST_ID") playlistId : String ,
                            @Query("type") type : String,
                            @Query("last_timestamp_ms") lastTimeStamp : String,
                            @Query("sort") sort : String,
                            @Header(PARAM_API_KEY) api_key : String) :
            Observable<UpNextPodCastPlaylistsVO>

    @GET(GET_PODCAST_CATEGORY)
    fun getPodCastCategory(@Query("top_level_only") num : Int,@Header(PARAM_API_KEY) api_key: String) :
            Observable<PodCastCategoryResponse>

    @GET(GET_EPISODE_DETAIL)
    fun getEpisodeDetail(@Path("id") episodeId : String,@Header(PARAM_API_KEY) api_key: String) :
            Observable<DetailVO>

}