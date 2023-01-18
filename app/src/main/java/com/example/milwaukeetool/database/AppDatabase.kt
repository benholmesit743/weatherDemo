package com.example.milwaukeetool.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.milwaukeetool.converters.Converters
import com.example.milwaukeetool.data.CapitalData

@Database(entities = [CapitalData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): AppDao
}
