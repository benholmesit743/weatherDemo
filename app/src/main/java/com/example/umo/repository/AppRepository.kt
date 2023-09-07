package com.example.umo.repository

import androidx.annotation.WorkerThread
import com.example.umo.data.CapitalData
import com.example.umo.database.AppDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDao) {
    fun getAll(): Flow<List<CapitalData>> = appDao.getAll()

    fun getById(id: String): Flow<CapitalData> = appDao.getById(id)

    @WorkerThread
    suspend fun insert(item: CapitalData) {
        appDao.insert(item)
    }

    @WorkerThread
    suspend fun addAll(list: List<CapitalData>) {
        appDao.addAll(list)
    }

    fun deleteAll() {
        appDao.deleteAll()
    }
}
