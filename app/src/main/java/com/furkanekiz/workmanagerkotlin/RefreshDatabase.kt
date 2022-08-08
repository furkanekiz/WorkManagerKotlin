package com.furkanekiz.workmanagerkotlin

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class RefreshDatabase(private val context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    override fun doWork(): Result {
        val getData = inputData
        val myNumber = getData.getInt("intKey",0)
        refreshDatabase(myNumber)
        return Result.success()
    }

    private fun refreshDatabase(myNumber: Int){
        val sharedPreferences = context.getSharedPreferences("com.furkanekiz.workmanagerkotlin",Context.MODE_PRIVATE)
        var mySavedNum = sharedPreferences.getInt("myNumber",0)
        mySavedNum += myNumber
        println(mySavedNum)
        sharedPreferences.edit().putInt("myNumber",mySavedNum).apply()
    }
}