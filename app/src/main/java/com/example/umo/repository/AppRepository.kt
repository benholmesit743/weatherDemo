package com.example.umo.repository

import androidx.annotation.WorkerThread
import com.example.umo.data.ZipCode
import com.example.umo.database.AppDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDao) {
    fun getAll(): Flow<List<ZipCode>> = appDao.getAll()

    fun getById(id: String): Flow<ZipCode> = appDao.getById(id)

    suspend fun zipExists(zip: String): Boolean = appDao.zipExists(zip)

    @WorkerThread
    suspend fun insert(item: ZipCode) {
        appDao.insert(item)
    }

    @WorkerThread
    suspend fun update(item: ZipCode) {
        appDao.update(item)
    }

    fun deleteAll() {
        appDao.deleteAll()
    }
}
