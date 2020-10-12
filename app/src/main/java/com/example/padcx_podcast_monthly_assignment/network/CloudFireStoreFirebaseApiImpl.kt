package com.example.padcx_podcast_monthly_assignment.network

import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.model.impls.PodCastModelImpl
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodcastVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.toObservable
import java.util.*
import kotlin.collections.ArrayList

object CloudFireStoreFirebaseApiImpl : FirebaseApi {

    private val database = Firebase.firestore
    private val mPodCastModel : PodCastModel = PodCastModelImpl

    override fun getLatestEpisodeFromFirebase(
        getupNextPodcastLists: (upNextPodcast: ArrayList<UpNextPodCastDataVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
        database.collection("latest_episodes")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFialure(it.message ?: "Please check connection")
                } ?: run {
                     mPodCastModel.deleteAllUpNextPodcast()
                    val upNextPodcastLists: MutableList<UpNextPodCastDataVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result){
                        val data = document.data
                        var podCast = UpNextPodCastDataVO()
                        podCast.image= data?.get("image") as String
                        podCast.description = data["description"] as String
                        podCast.id = data["id"] as String
                        podCast.audio =data["audio"] as String
                        podCast.audio_length_sec = (data["audio_length_sec"] as Long).toInt()
                        podCast.explicit_content = data["explicit_content"] as Boolean
                        podCast.link = data["link"] as String
                        podCast.listennotes_edit_url = data["listennotes_edit_url"] as String
                        podCast.listennotes_url = data["listennotes_url"] as String
                        podCast.maybe_audio_invalid = data["maybe_audio_invalid"] as Boolean
                        podCast.pub_date_ms = (data["pub_date_ms"] as Long)
                        podCast.thumbnail = data["thumbnail"] as String
                        podCast.title = data["title"] as String

                        bindPodCastData(data, podCast)

                        upNextPodcastLists.add(podCast)
                    }
                    getupNextPodcastLists(upNextPodcastLists as ArrayList<UpNextPodCastDataVO>)
                }
            }
    }

    private fun bindPodCastData(
        data: Map<String, Any>,
        podCast: UpNextPodCastDataVO
    ) {
        val podCastDataItem: Map<String, Any> = data["podcast"] as Map<String,Any>
        val podcast = PodcastVO()
        podcast.podcast_id = podCastDataItem["id"] as String
        podcast.podcast_image = podCastDataItem["image"] as String
        podcast.podcast_listennotes_url = podCastDataItem["listennotes_url"] as String
        podcast.podcast_publisher = podCastDataItem["publisher"] as String
        podcast.podcast_thumbnail = podCastDataItem["thumbnail"] as String
        podcast.podcast_title = podCastDataItem["title"] as String
        podCast.podcast = podcast
    }

    override fun getAllCategoriesFromFirebase(
        getAllCategories: (categoryLists: ArrayList<PodCastCategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        database.collection("genres")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {

                    val categoryLists = arrayListOf<PodCastCategoryVO>()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result){
                        val data = document.data
                        var category = PodCastCategoryVO()
                        category.id = (data?.get("id") as Long).toInt()
                        category.name = data["name"] as String
                        category.parent_id = (data["parent_id"] as Long).toInt()

                        categoryLists.add(category)
                    }

                    getAllCategories(categoryLists)

                }
            }
    }

//    override fun getRandomPodCast(randomPodCast: (podCast: PodCastDataVO) -> Unit) {
//        database.collection("latest_episodes")
//            .addSnapshotListener { value, error ->
//                error?.let {
//
//                } ?: run {
//
//                    val result = value?.documents ?: arrayListOf()
//
//                    var childrenCount: Int = result.size
//                    var count = childrenCount.toInt()
//                    var randomNumber = Random().nextInt(count)
//
//                    var i = 0
//                    var Podcast = PodCastDataVO()
//
//
//                    for (document in result){
//
//                        val data = document.data
//
//                        if (i == randomNumber){
//                            Podcast.image= data?.get("image") as String
//                            Podcast.description = data["description"] as String
//                            Podcast.id = data["id"] as String
//                            Podcast.audio =data["audio"] as String
//                            Podcast.audio_length_sec = (data["audio_length_sec"] as Long).toInt()
//                            Podcast.explicit_content = data["explicit_content"] as Boolean
//                            Podcast.link = data["link"] as String
//                            Podcast.listennotes_edit_url = data["listennotes_edit_url"] as String
//                            Podcast.listennotes_url = data["listennotes_url"] as String
//                            Podcast.maybe_audio_invalid = data["maybe_audio_invalid"] as Boolean
//
//                            Podcast.pub_date_ms = (data["pub_date_ms"] as Long)
//                            Podcast.thumbnail = data["thumbnail"] as String
//                            Podcast.title = data["title"] as String
//                            break
//                        }
//                        i++
//                    }
//
//                  randomPodCast(Podcast)
//                }
//
//
//            }
//    }
}

