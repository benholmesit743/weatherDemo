package com.example.umo.data

data class ApiResult(
    val data: PrimaryData?,
    val location: LocationData?,
)

data class PrimaryData(
    val time: String?,
    val values: DataValues?
)

data class LocationData(
    val lat: Float,
    val lon: Float,
    val name: String,
    val type: String
)

data class DataValues(
    val temperature: Double?,
    val temperatureApparent: Int,
    val uvIndex: Int,
    val visibility: Double,
    val weatherCode: Int
)

fun ApiResult.toZipCode (currentZipCode: String, currentUnit: Int) : ZipCode{
    return ZipCode(
        timeStamp = data?.time,
        temperature = data?.values?.temperature,
        zipCode = currentZipCode,
        unit = currentUnit
    )
}
