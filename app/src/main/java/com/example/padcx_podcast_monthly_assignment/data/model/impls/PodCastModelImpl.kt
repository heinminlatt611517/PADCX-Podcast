package com.example.padcx_podcast_monthly_assignment.data.model.impls

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.padcx_podcast_monthly_assignment.BuildConfig
import com.example.padcx_podcast_monthly_assignment.data.model.BaseModel
import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.vos.*
import com.example.padcx_podcast_monthly_assignment.network.CloudFireStoreFirebaseApiImpl
import com.example.padcx_podcast_monthly_assignment.network.FirebaseApi
import com.example.padcx_podcast_monthly_assignment.network.RealTimeDatabaseFirebaseApiImpl
import com.example.padcx_podcast_monthly_assignment.utils.EM_NO_INTERNET_CONNECTION
import com.example.padcx_podcast_monthly_assignment.utils.startDownloading
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers

object PodCastModelImpl : BaseModel(),PodCastModel {

    //override var mFirebaseApi: FirebaseApi = RealTimeDatabaseFirebaseApiImpl
    override var mFirebaseApi: FirebaseApi = CloudFireStoreFirebaseApiImpl


    override fun getPodCast(onError: (String) -> Unit): LiveData<PodCastDataVO> {
        return mDatabase.podcastDao().getPodCast()
    }

    override fun getAllUpNextPodCastFromApiAndSaveToDatabase(
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {

        mFirebaseApi.getLatestEpisodeFromFirebase(getupNextPodcastLists = {
            it.toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mDatabase.podcastDao().insertAllUpNextPodCast(it)

                }
        },
        onFialure = {})


        //        mPodCastApi.getPodCastPlaylists("m1pe7z60bsw","episode_list","0","recent_added_first",BuildConfig.API_KEY)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//            mDatabase.podcastDao().insertAllUpNextPodCast(it)
//        },{
//            onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
//        })


    }

    override fun getAllUpNextPodCast(onError: (String) -> Unit): LiveData<List<UpNextPodCastDataVO>> {

        return mDatabase.podcastDao().getAllUpNextPodCast()

    }

    override fun getAllPodCastCategoryFromApiAndSaveToDatabase(
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {

        mFirebaseApi.getAllCategoriesFromFirebase(getAllCategories = {
            it.toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    mDatabase.podcastDao().insertAllPodCastCategory(it)
                }
        },
        onFailure = {})

        //        mPodCastApi.getPodCastCategory(0,BuildConfig.API_KEY)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map { it.genres }
//            .subscribe({
//                mDatabase.podcastDao().insertAllPodCastCategory(it)
//            },{
//                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
//            })

    }

    override fun getAllPodCastCategory(onError: (String) -> Unit): LiveData<List<PodCastCategoryVO>> {
       return mDatabase.podcastDao().getAllPodCastCategory()
    }

    override fun getPodCastById(id: String) : LiveData<UpNextPodCastDataVO> {
        //return mPodCastApi.getEpisodeDetail(id,BuildConfig.API_KEY)
      return  mDatabase.podcastDao().getPodCastDetailByID(id)
    }



    override fun deleteAllUpNextPodcast() {
        mDatabase.podcastDao().deleteAllUpNextPodCast()
    }

    override fun getRandomPodCastFromDatabase() : LiveData<UpNextPodCastDataVO> {

      return  mDatabase.podcastDao().getRandomPodCast()
    }


    /***
     * get podCast data from firebase
     */

//    override fun getCategory(
//        onSuccess: (categoryLists: ArrayList<PodCastCategoryVO>) -> Unit,
//        getRandomPodcast : (randomPodcast : PodCastDataVO) -> Unit,
//        getupNextPodcastLists: (upNextPodcast: ArrayList<UpNextVO>) -> Unit,
//        onFialure: (String) -> Unit
//    ) {
//
//        mFirebaseApi.getCategory(onSuccess,getRandomPodcast,getupNextPodcastLists,onFialure)
//
//    }

    override fun getRandomPodCast(randomPodCast: (podCast: PodCastDataVO) -> Unit) {
        // mFirebaseApi.getRandomPodCast(randomPodCast)
    }

    override fun getDetailFromApiAndSaveToDatabase(
        episodeId: String,
        onSuccess: (detailVO: DetailVO) -> Unit,
        onError: (String) -> Unit
    ) {
        mPodCastApi.getEpisodeDetail(episodeId,BuildConfig.API_KEY)
            .map { it }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                it?.let{data-> mDatabase.podcastDao().insertDetailData(data) }
            },{
                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
            })
    }

    override fun getDetailEpisodeData(
        episodeId: String,
        onError: (String) -> Unit
    ): LiveData<DetailVO> {
        return mDatabase.podcastDao().getAllDetailDataByEpisodeID(episodeId)
    }
    override fun startDownloadPodcast(context: Context, dataVO: UpNextPodCastDataVO) {
        startDownloading(context,dataVO)
    }

    override fun getAllDownloadPodcastList(onError: (String) -> Unit): LiveData<List<DownloadPodCastDataVO>> {
        return mDatabase.podcastDao().getAllDownloadPodcastData()
    }

    override fun saveDownloadPodcastItem(
        donwloadVO: DownloadPodCastDataVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mDatabase.podcastDao().insertDownloadPodcastData(donwloadVO)

    }

    override fun getDownloadPodCast(): LiveData<List<DownloadPodCastDataVO>> {
        return mDatabase.podcastDao().getAllDownloadPodCast()
    }

    @SuppressLint("CheckResult")
    override fun getPodCastFromApiAndSaveToDatabase(
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {
//
//        mFirebaseApi.getRandomPodCast {
//            mDatabase.podcastDao().insertPodCast(it)
//        }
//        mPodCastApi.getRandomPodCastEpisode(BuildConfig.API_KEY)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                  mDatabase.podcastDao().insertPodCast(it)
//            },{
//                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
//            })
    }



}