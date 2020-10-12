package com.example.padcx_podcast_monthly_assignment.worker

import android.content.Context
import androidx.work.WorkerParameters

class GetPodCastWorker (context: Context,workerParameters: WorkerParameters) : BaseWorker(context,workerParameters) {
    override fun doWork(): Result {
        var result = Result.failure()

//        mPodCastModel.getPodCastFromApiAndSaveToDatabase(
//            onSuccess = {
//                result = Result.success()
//            },
//            onError = {
//                result = Result.failure()
//            }
//        )


        mPodCastModel.getAllUpNextPodCastFromApiAndSaveToDatabase(
            onSuccess = {
                result = Result.success()
            },
            onError = {
                result = Result.failure()
            }
        )

        mPodCastModel.getAllPodCastCategoryFromApiAndSaveToDatabase(
            onSuccess = {
                result = Result.success()
            },
            onError = {
                result = Result.failure()
            }
        )

        return result
    }

}