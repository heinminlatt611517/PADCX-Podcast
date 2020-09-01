package com.example.padcx_podcast_monthly_assignment.root

import android.app.Application
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.padcx_podcast_monthly_assignment.data.model.impls.PodCastModelImpl
import com.example.padcx_podcast_monthly_assignment.worker.GetPodCastWorker

class PodcastApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PodCastModelImpl.initDatabase(applicationContext)

        getPodCastOneTime()

    }

    private fun getPodCastOneTime() {
        val getEventsWorkRequest = OneTimeWorkRequest
            .Builder(GetPodCastWorker::class.java)
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(getEventsWorkRequest)
    }


}