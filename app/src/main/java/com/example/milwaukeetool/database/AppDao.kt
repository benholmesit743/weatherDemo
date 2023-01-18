package com.example.milwaukeetool.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.milwaukeetool.data.CapitalData
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM capital_data")
    fun getAll(): Flow<List<CapitalData>>

    @Query("SELECT * FROM capital_data WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<CapitalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(capitalData: CapitalData)


//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): CapitalData
//
//    @Insert
//    fun insertAll(vararg users: User)
//
//    @Delete
//    fun delete(user: User)
}
