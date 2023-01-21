package com.example.milwaukeetool.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.milwaukeetool.data.*
import com.example.milwaukeetool.repository.AppRepository
import com.example.milwaukeetool.retrofit.ApiService
import com.example.milwaukeetool.utilities.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class DownloadManager(context: Context, userParameters: WorkerParameters) :
    CoroutineWorker(context, userParameters) {

    private val apiService: ApiService by inject(ApiService::class.java)
    private val repository: AppRepository by inject(AppRepository::class.java)

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val dates = Utility.getStartAndEndDates()
                for (item in CapitalCoordinates.values()) {
                    repository.insert(apiService.getWeather(
                        latitude = item.lat, longitude = item.lon,
                        startDate = dates[0], endDate = dates[1]
                    ).blockingGet().toCapitalData(item.getState(), item.capital, item.lat, item.lon))
                }
                Result.success()
            } catch (error: Throwable) {
                Result.failure()
            }
        }
    }
}