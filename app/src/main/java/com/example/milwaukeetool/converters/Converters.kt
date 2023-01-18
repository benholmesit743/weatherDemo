package com.example.milwaukeetool.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun doubleListToJson(value: List<Double>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonDoubleToList(value: String) = Gson().fromJson(value, Array<Double>::class.java).toList()
}