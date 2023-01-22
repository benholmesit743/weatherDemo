package com.example.milwaukeetool.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "capital_data")
data class CapitalData(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "capital") val capital: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "hourlyTemps") val hourlyTemps: List<Double> = ArrayList(),
    @ColumnInfo(name = "hiTemps") val hiTemp: List<Double> = ArrayList(),
    @ColumnInfo(name = "lowTemps") val lowTemp: List<Double> = ArrayList(),
    @ColumnInfo(name = "precipitation") val precipitation: List<Double> = ArrayList(),
    @ColumnInfo(name = "timeStamp") val timeStamp: String,
    )

sealed class ForecastAdapterData{
    data class DailyForecast(
        val currentTemp: Double,
        val hiTemp: Double,
        val lowTemp: Double,
        val precipitation: Double,
        val timeStamp: String
    ) : ForecastAdapterData()
    data class Title(val day: Int) : ForecastAdapterData()
}

fun CapitalData.toForecastAdapterData(): ArrayList<ForecastAdapterData> {
    val result = ArrayList<ForecastAdapterData>()
    var currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    for (index in hiTemp.indices) {
        result.add(ForecastAdapterData.Title(index + 1))
        result.add(ForecastAdapterData.DailyForecast(
            currentTemp = if (currentHour < hourlyTemps.size) hourlyTemps[currentHour] else 0.00,
            hiTemp = hiTemp[index],
            lowTemp = if (index < lowTemp.size) lowTemp[index] else 0.00,
            precipitation = if (index < precipitation.size) precipitation[index] else 0.00,
            timeStamp = timeStamp
        ))
        currentHour += 24
    }
    return result
}

fun CapitalData.getCurrentTemp(): Double {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return if (currentHour < hourlyTemps.size) hourlyTemps[currentHour] else 0.00
}

fun CapitalData.getHiTemp(): Double {
    return hiTemp[0]
}

fun CapitalData.getLowTemp(): Double {
    return lowTemp[0]
}