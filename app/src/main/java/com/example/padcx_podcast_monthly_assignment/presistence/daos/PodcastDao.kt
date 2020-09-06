package com.example.padcx_podcast_monthly_assignment.presistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.padcx_podcast_monthly_assignment.data.vos.*

@Dao
interface PodcastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPodCast(podCast : PodCastDataVO)

    @Query("SELECT * FROM podCast")
    fun getPodCast(): LiveData<PodCastDataVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUpNextPodCast(upNextPodCast : UpNextPodCastPlaylistsVO)

    @Query("SELECT * FROM upNextPodCast")
    fun getAllUpNextPodCast(): LiveData<UpNextPodCastPlaylistsVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPodCastCategory(upNextPodCast : List<PodCastCategoryVO>)

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



}