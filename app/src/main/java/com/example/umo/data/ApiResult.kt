package com.example.umo.data

data class ApiResult(
    val time: String?,
    val values: DataValues?,
)

data class DataValues(
    val temperature: Double?
)
