package com.example.milwaukeetool.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.milwaukeetool.data.CapitalData
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {
    @Query("SELECT * FROM capital_data ORDER BY state ASC")
    fun getAll(): Flow<List<CapitalData>>

    @Query("SELECT * FROM capital_data WHERE uid IN (:id)")
    fun getById(id: String): Flow<CapitalData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(capitalData: CapitalData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<CapitalData>)

    @Query("DELETE FROM capital_data")
    fun deleteAll()
}
