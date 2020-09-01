package com.example.padcx_podcast_monthly_assignment.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.padcx_podcast_monthly_assignment.data.model.PodCastModel
import com.example.padcx_podcast_monthly_assignment.data.model.impls.PodCastModelImpl
import java.lang.reflect.Parameter

abstract class BaseWorker(context: Context,workerParameter: WorkerParameters) : Worker(context,workerParameter) {
    val mPodCastModel : PodCastModel = PodCastModelImpl
}