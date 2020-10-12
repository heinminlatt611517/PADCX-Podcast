package com.example.padcx_podcast_monthly_assignment.network

import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.presistence.db.PodcastDb

interface FirebaseApi {

    fun getLatestEpisodeFromFirebase(getupNextPodcastLists: (upNextPodcast: ArrayList<UpNextPodCastDataVO>) -> Unit,
                    onFialure: (String) -> Unit)

    fun getAllCategoriesFromFirebase(getAllCategories : (categoryLists : ArrayList<PodCastCategoryVO>) -> Unit,
    onFailure : (String) -> Unit)

//    fun getRandomPodCast(randomPodCast : (podCast : PodCastDataVO) -> Unit)
}