package com.example.padcx_podcast_monthly_assignment.presistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.padcx_podcast_monthly_assignment.data.vos.*

@Dao
interface PodcastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPodCast(podCast : PodCastDataVO)

    @Query("SELECT * FROM podCast")
    fun getPodCast(): LiveData<PodCastDataVO>





    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertAllUpNextPodCast(upNextPodCast : UpNextPodCastPlaylistsVO)
    fun insertAllUpNextPodCast(upNextPodCast : UpNextPodCastDataVO)

    @Query("SELECT * FROM upNext")
    fun getAllUpNextPodCast(): LiveData<List<UpNextPodCastDataVO>>

    @Query("DELETE  FROM upNext")
    fun deleteAllUpNextPodCast()

    @Query("SELECT * FROM upNext order by RANDOM() limit 1")
    fun getRandomPodCast() : LiveData<UpNextPodCastDataVO>

    @Query("select * from upNext WHERE id = :podcastID")
    fun getPodCastDetailByID(podcastID : String) : LiveData<UpNextPodCastDataVO>






    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPodCastCategory(upNextPodCast :PodCastCategoryVO)

    @Query("SELECT * FROM podCastCategory")
    fun getAllPodCastCategory(): LiveData<List<PodCastCategoryVO>>




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDownloadPodCast(podCast: DownloadPodCastDataVO)

    @Query("SELECT * FROM download")
    fun getAllDownloadPodCast(): LiveData<List<DownloadPodCastDataVO>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDownloadPodcastData(podcasts: DownloadPodCastDataVO)


    @Query("select * from download")
    fun getAllDownloadPodcastData(): LiveData<List<DownloadPodCastDataVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailData(data: DetailVO)

    @Query("select * from detail WHERE id = :detail_id")
    fun getAllDetailDataByEpisodeID(detail_id : String): LiveData<DetailVO>




}