package com.example.umo.data

data class ApiResult(
    val time: String?,
    val values: DataValues?,
)

data class DataValues(
    val temperature: Double?
)

fun ApiResult.toZipCode (currentZipCode: String, currentUnit: Int) : ZipCode{
    return ZipCode(
        timeStamp = time,
        temperature = values?.temperature,
        zipCode = currentZipCode,
        unit = currentUnit
    )
}
