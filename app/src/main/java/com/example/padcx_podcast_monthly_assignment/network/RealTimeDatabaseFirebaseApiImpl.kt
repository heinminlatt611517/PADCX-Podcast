package com.example.padcx_podcast_monthly_assignment.network

import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.model.impls.PodCastModelImpl
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastCategoryVO
import com.example.padcx_podcast_monthly_assignment.data.vos.PodCastDataVO
import com.example.padcx_podcast_monthly_assignment.data.vos.UpNextPodCastDataVO
import com.example.padcx_podcast_monthly_assignment.presistence.db.PodcastDb
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

object RealTimeDatabaseFirebaseApiImpl : FirebaseApi {

    private val database: DatabaseReference = Firebase.database.reference
    private val mPodCastModel : PodCastModel = PodCastModelImpl


    override fun getLatestEpisodeFromFirebase(
        getupNextPodcastLists: (upNextPodcast: ArrayList<UpNextPodCastDataVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
        database.child("latest_episodes")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    onFialure(error.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    mPodCastModel.deleteAllUpNextPodcast()
                    val upNextPodcastLists = arrayListOf<UpNextPodCastDataVO>()
                    snapshot.children.forEach { dataSnapShot ->
                        dataSnapShot.getValue(UpNextPodCastDataVO::class.java)?.let {
                            upNextPodcastLists.add(it)
                        }
                    }
                    getupNextPodcastLists(upNextPodcastLists)

                }
            })
    }

    override fun getAllCategoriesFromFirebase(
        getAllCategories: (categoryLists: ArrayList<PodCastCategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("genres")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val categoryLists = arrayListOf<PodCastCategoryVO>()

                    snapshot.children.forEach { dataSnapShot ->
                        dataSnapShot.getValue(PodCastCategoryVO::class.java)?.let {
                            categoryLists.add(it)
                        }
                    }
                    getAllCategories(categoryLists)
                }
            })
    }

//    override fun getRandomPodCast(randomPodCast: (podCast: PodCastDataVO) -> Unit) {
//        database.child("latest_episodes")
//            .addValueEventListener(object : ValueEventListener {
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//
//                    var childrenCount: Long = snapshot.childrenCount
//                    var count = childrenCount.toInt()
//                    var randomNumber = Random().nextInt(count)
//
//                    var i = 0
//                    var randomPodcast = PodCastDataVO()
//
//                    for (snap in snapshot.children) {
//                        if (i == randomNumber) {
//
//                            randomPodcast = snap.getValue(PodCastDataVO::class.java)!!
//                            break
//                        }
//                        i++
//                    }
//
//                    randomPodCast(randomPodcast)
//
//                }
//            })
//    }
}

