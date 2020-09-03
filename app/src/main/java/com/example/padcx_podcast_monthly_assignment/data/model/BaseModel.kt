package com.example.padcx_podcast_monthly_assignment.data.model

import android.content.Context
import com.example.padcx_podcast_monthly_assignment.BuildConfig
import com.example.padcx_podcast_monthly_assignment.network.PodcastApi
import com.example.padcx_podcast_monthly_assignment.presistence.db.PodcastDb
import com.example.padcx_podcast_monthly_assignment.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {

    protected lateinit var mDatabase : PodcastDb
    protected var mPodCastApi : PodcastApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_FIELD)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


        mPodCastApi = retrofit.create(PodcastApi::class.java)
    }

    fun initDatabase(context: Context){
        mDatabase=PodcastDb.getDbInstance(context)
    }

}