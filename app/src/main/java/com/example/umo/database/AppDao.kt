package com.example.umo.database

import androidx.room.*
import com.example.umo.data.ZipCode
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {
    @Query("SELECT * FROM zip_code ORDER BY zipCode ASC")
    fun getAll(): Flow<List<ZipCode>>

    @Query("SELECT * FROM zip_code WHERE zipCode IN (:zipCode)")
    fun getById(zipCode: String): Flow<ZipCode>

    @Query("SELECT EXISTS(SELECT * FROM zip_code WHERE zipCode = :zip)")
    suspend fun zipExists(zip : String) : Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(zipCode: ZipCode)

    @Update
    suspend fun update(zipCode: ZipCode)

    @Query("DELETE FROM zip_code")
    fun deleteAll()
}
