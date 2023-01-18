package com.example.milwaukeetool.retrofit

import com.example.milwaukeetool.data.ApiResult
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.open-meteo.com/v1/forecast?latitude=34.00&longitude=-81.03&hourly=temperature_2m&daily=temperature_2m_max,temperature_2m_min,precipitation_sum&temperature_unit=fahrenheit&windspeed_unit=mph&precipitation_unit=inch&timezone=America%2FNew_York&start_date=2023-01-17&end_date=2023-01-21
interface ApiService {

//    @GET("v1/forecast")
//    fun getWeather(@Query("latitude") latitude: Double,
//                   @Query("longitude") longitude: Double,
//                @Query("hourly") hourly: String = "temperature_2m",
//                   @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min,precipitation_sum",
//                   @Query("temperature_unit") tempUnit: String = "fahrenheit",
//                   @Query("windspeed_unit") windSpeedUnit: String = "mph",
//                   @Query("precipitation_unit") precipitationUnit: String = "inch",
//                   @Query("timezone") timeZone: String = "America/New_York",
//                   @Query("start_date") startDate: String = "2023-01-17",
//        @Query("end_date") endDate: String = "2023-01-21") : Single<ApiResult>

    @GET("forecast?latitude=34.00&longitude=-81.03")
    fun getWeather() : Single<ApiResult>

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