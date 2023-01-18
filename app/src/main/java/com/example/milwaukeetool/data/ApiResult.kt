package com.example.milwaukeetool.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ApiResult(
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val timezone_abbreviation: String?,
    val elevation: Int?,
    val hourly: Hourly?,
    val daily: Daily?
)

data class Hourly(
    val apparent_temperature: ArrayList<Double>?
)

data class Daily(
    val time: ArrayList<String>?,
    val temperature_2m_max: ArrayList<Double>?,
    val temperature_2m_min: ArrayList<Double>?,
    val precipitation_sum: ArrayList<Double>?
)

//@PrimaryKey
//val uid: Int,
//@ColumnInfo(name = "state") val state: String?,
//@ColumnInfo(name = "capital") val capital: String?,
//@ColumnInfo(name = "latitude") val latitude: Double?,
//@ColumnInfo(name = "longitude") val longitude: Double?,
//@ColumnInfo(name = "hourlyTemps") val hourlyTemps: List<Double> = ArrayList(),
//@ColumnInfo(name = "hiTemps") val hiTemp: List<Double> = ArrayList(),
//@ColumnInfo(name = "lowTemps") val lowTemp: List<Double> = ArrayList(),
//@ColumnInfo(name = "precipitation") val precipitation: List<Double> = ArrayList()
fun ApiResult.toCapitalData(): CapitalData {
    return CapitalData(
        state =
    )
}
