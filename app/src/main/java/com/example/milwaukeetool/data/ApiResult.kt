package com.example.milwaukeetool.data

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

fun ApiResult.toCapitalData(state: String, capital: String, lat: Double, lon: Double): CapitalData {
    return CapitalData(
        uid = "${state}_${capital}",
        state = state,
        capital = capital,
        latitude = lat,
        longitude = lon,
        hourlyTemps = hourly?.apparent_temperature ?: ArrayList(),
        hiTemp = daily?.temperature_2m_max ?: ArrayList(),
        lowTemp = daily?.temperature_2m_min ?: ArrayList(),
        precipitation = daily?.precipitation_sum ?: ArrayList()
    )
}
