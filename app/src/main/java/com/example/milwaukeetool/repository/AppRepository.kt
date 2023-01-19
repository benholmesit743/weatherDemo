package com.example.milwaukeetool.repository

import androidx.annotation.WorkerThread
import com.example.milwaukeetool.data.CapitalData
import com.example.milwaukeetool.database.AppDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDao) {
    fun getAll(): Flow<List<CapitalData>> = appDao.getAll()

    fun getById(id: String): Flow<CapitalData> = appDao.getById(id)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: CapitalData) {
        appDao.insert(item)
    }

    fun deleteAll() {
        appDao.deleteAll()
    }
}
