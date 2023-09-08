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

fun Int.toUnits() : String {
    return when(this) {
        0 -> "imperial"
        else -> "metric"
    }
}

//fun CapitalData.toForecastAdapterData(): ArrayList<ForecastAdapterData> {
//    val result = ArrayList<ForecastAdapterData>()
//    var currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
//    for (index in hiTemp.indices) {
//        result.add(ForecastAdapterData.Title(index + 1))
//        result.add(ForecastAdapterData.DailyForecast(
//            currentTemp = if (currentHour < hourlyTemps.size) hourlyTemps[currentHour] else 0.00,
//            hiTemp = hiTemp[index],
//            lowTemp = if (index < lowTemp.size) lowTemp[index] else 0.00,
//            precipitation = if (index < precipitation.size) precipitation[index] else 0.00,
//            timeStamp = timeStamp
//        ))
//        currentHour += 24
//    }
//    return result
//}
//
//fun CapitalData.getCurrentTemp(): Double {
//    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
//    return if (currentHour < hourlyTemps.size) hourlyTemps[currentHour] else 0.00
//}
//
//fun CapitalData.getHiTemp(): Double {
//    return hiTemp[0]
//}
//
//fun CapitalData.getLowTemp(): Double {
//    return lowTemp[0]
//}