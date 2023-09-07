package com.example.umo.repository

import androidx.annotation.WorkerThread
import com.example.umo.data.ZipCode
import com.example.umo.database.AppDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDao) {
    fun getAll(): Flow<List<ZipCode>> = appDao.getAll()

    fun getById(id: String): Flow<ZipCode> = appDao.getById(id)

    @WorkerThread
    suspend fun insert(item: ZipCode) {
        appDao.insert(item)
    }

    fun deleteAll() {
        appDao.deleteAll()
    }
}
