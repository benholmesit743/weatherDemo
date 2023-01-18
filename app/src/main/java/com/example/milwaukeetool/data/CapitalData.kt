package com.example.milwaukeetool.data

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class CapitalData(
    val state: String?,
    val capital: String?,
    val latitude: Double,
    val longitude: Double,
    val hiTemp: Int?,
    val lowTemp: Int?,
    val precipitation: Double?
) : Parcelable
