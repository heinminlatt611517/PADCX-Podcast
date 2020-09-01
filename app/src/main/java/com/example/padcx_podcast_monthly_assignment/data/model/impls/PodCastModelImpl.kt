package com.example.padcx_podcast_monthly_assignment.data.model.impls

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.example.padcx_podcast_monthly_assignment.BuildConfig
import com.example.padcx_podcast_monthly_assignment.data.model.BaseModel
import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.vos.DetailVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastPlaylistsVO
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PodCastModelImpl : BaseModel(),PodCastModel {

    @SuppressLint("CheckResult")
    override fun getPodCastFromApiAndSaveToDatabase(
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {
        mPodCastApi.getRandomPodCastEpisode(BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                  mDatabase.podcastDao().insertPodCast(it)
            },{
                onError(it.localizedMessage)
            })
    }

    override fun getPodCast(onError: (String) -> Unit): LiveData<PodCastDataVO> {
        return mDatabase.podcastDao().getPodCast()
    }

    override fun getAllUpNextPodCastFromApiAndSaveToDatabase(
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {
        mPodCastApi.getPodCastPlaylists(BuildConfig.PLAYLIST_ID,"episode_list","0","recent_added_first",BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            mDatabase.podcastDao().insertAllUpNextPodCast(it)
        },{
            onError(it.localizedMessage)
        })
    }

    override fun getAllUpNextPodCast(onError: (String) -> Unit): LiveData<UpNextPodCastPlaylistsVO> {

        return mDatabase.podcastDao().getAllUpNextPodCast()

    }

    override fun getAllPodCastCategoryFromApiAndSaveToDatabase(
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {
        mPodCastApi.getPodCastCategory(0,BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.genres }
            .subscribe({
                mDatabase.podcastDao().insertAllPodCastCategory(it)
            },{
                onError(it.localizedMessage)
            })
    }

    override fun getAllPodCastCategory(onError: (String) -> Unit): LiveData<List<PodCastCategoryVO>> {
       return mDatabase.podcastDao().getAllPodCastCategory()
    }




    override fun getPodCastById(id: String) : Observable<DetailVO> {
        return mPodCastApi.getEpisodeDetail(id,BuildConfig.API_KEY)
    }


}