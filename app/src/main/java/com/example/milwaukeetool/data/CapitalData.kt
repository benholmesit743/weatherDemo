package com.example.milwaukeetool.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "capital_data")
data class CapitalData(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "state") val state: String?,
    @ColumnInfo(name = "capital") val capital: String?,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "hiTemp") val hiTemp: Int?,
    @ColumnInfo(name = "lowTemp") val lowTemp: Int?,
    @ColumnInfo(name = "precipitation") val precipitation: Double?
)
