package com.example.milwaukeetool.repository

import androidx.annotation.WorkerThread
import com.example.milwaukeetool.data.CapitalData
import com.example.milwaukeetool.database.AppDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<CapitalData>> = appDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: CapitalData) {
        appDao.insert(item)
    }
}
