package com.example.milwaukeetool.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.milwaukeetool.data.CapitalData

@Database(entities = [CapitalData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): AppDao
}
