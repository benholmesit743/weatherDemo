package com.example.umo.repository

import androidx.annotation.WorkerThread
import com.example.umo.data.CapitalData
import com.example.umo.database.AppDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDao) {
    fun getAll(): Flow<List<String>> = appDao.getAll()

    @WorkerThread
    suspend fun insert(item: String) {
        appDao.insert(item)
    }

    fun deleteAll() {
        appDao.deleteAll()
    }
}
