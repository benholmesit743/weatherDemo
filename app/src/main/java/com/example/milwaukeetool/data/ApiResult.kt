package com.example.milwaukeetool.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ApiResult(
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val timezone_abbreviation: String?,
    val elevation: Int?,
)


//@Parcelize
//data class ApiResult(
//    val timezone: String?,
//) : Parcelable
