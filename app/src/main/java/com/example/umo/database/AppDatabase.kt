package com.example.umo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.umo.data.ZipCode

@Database(entities = [ZipCode::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): AppDao
}
