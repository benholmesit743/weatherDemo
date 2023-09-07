package com.example.umo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.umo.data.ZipCode
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {
    @Query("SELECT * FROM zip_code")
    fun getAll(): Flow<List<ZipCode>>

    @Query("SELECT * FROM zip_code WHERE zipCode IN (:zipCode)")
    fun getById(zipCode: String): Flow<ZipCode>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(zipCode: ZipCode)

    @Query("DELETE FROM zip_code")
    fun deleteAll()
}
