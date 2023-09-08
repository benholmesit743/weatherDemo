package com.example.umo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zip_code")
data class ZipCode(
    @PrimaryKey @ColumnInfo(name = "zipCode") val zipCode: String,
    @ColumnInfo(name = "temperature") val temperature: Double?,
    @ColumnInfo(name = "timeStamp") val timeStamp: String?,
    //0 = Imperial, 1 = Metric
    @ColumnInfo(name = "unit") var unit: Int
    )

fun Int.toUnits() : String {
    return when(this) {
        0 -> "imperial"
        else -> "metric"
    }
}