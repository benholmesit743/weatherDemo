package com.example.milwaukeetool.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "capital_data")
data class CapitalData(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "state") val state: String?,
    @ColumnInfo(name = "capital") val capital: String?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "hourlyTemps") val hourlyTemps: List<Double> = ArrayList(),
    @ColumnInfo(name = "hiTemps") val hiTemp: List<Double> = ArrayList(),
    @ColumnInfo(name = "lowTemps") val lowTemp: List<Double> = ArrayList(),
    @ColumnInfo(name = "precipitation") val precipitation: List<Double> = ArrayList()
)
