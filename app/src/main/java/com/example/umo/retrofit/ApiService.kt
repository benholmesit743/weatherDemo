package com.example.umo.retrofit

import com.example.umo.data.ApiResult
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("realtime")
    fun getWeather(@Query("location") location: String,
                   @Query("units") units: String,
                   @Query("apikey") apikey: String
    ): Single<ApiResult>

    companion object {
        fun create(client: OkHttpClient) : ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.tomorrow.io/v4/weather/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}