package com.example.test.user.presentation.service

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyPeriodicWorker(appContext:Context, workerParameters: WorkerParameters) : Worker(appContext,workerParameters) {
    override fun doWork(): Result {
        Log.d("WorkManager", "Periodic work is running!")
        return Result.success()
    }


}