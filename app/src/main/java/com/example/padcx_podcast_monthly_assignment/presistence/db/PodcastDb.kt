package com.example.padcx_podcast_monthly_assignment.presistence.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.padcx_podcast_monthly_assignment.presistence.daos.PodcastDao

abstract class PodcastDb : RoomDatabase() {
    companion object{
        val DB_NAME = "PODCAST.DB"
        var dbInstance : PodcastDb? = null

        fun getDbInstance(context: Context) : PodcastDb{
            when(dbInstance){
                null ->{
                    dbInstance=Room.databaseBuilder(context,PodcastDb::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            val i = dbInstance
            return i!!
        }
    }

    abstract fun podcastDao() : PodcastDao
}