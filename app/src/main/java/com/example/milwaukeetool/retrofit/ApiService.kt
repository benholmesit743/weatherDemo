package com.example.milwaukeetool.retrofit

import com.example.milwaukeetool.data.ApiResult
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    fun getWeather(@Query("latitude") latitude: Double,
                   @Query("longitude") longitude: Double,
                   @Query("hourly") hourly: String = "apparent_temperature",
                   @Query("daily", encoded = true) daily: String = "temperature_2m_max,temperature_2m_min,precipitation_sum",
                   @Query("temperature_unit") tempUnit: String = "fahrenheit",
                   @Query("windspeed_unit") windSpeedUnit: String = "mph",
                   @Query("precipitation_unit") precipitationUnit: String = "inch",
                   @Query("timezone") timeZone: String = "America/New_York",
                   @Query("start_date") startDate: String,
                   @Query("end_date") endDate: String) : Single<ApiResult>

    companion object {
        fun create(client: OkHttpClient) : ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.open-meteo.com/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}