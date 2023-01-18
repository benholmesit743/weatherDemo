package com.example.milwaukeetool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val apiService = ApiService.create()
//        apiService.getWeather().enqueue(object : Callback<ApiResult> {
//            override fun onResponse(call: Call<ApiResult>, response: Response<ApiResult>) {
//                    if(response.body() != null) {
//                        Log.e("Success", "Success")
//                        val t = 100
//                        var result = t + 10
//                    }
//            }
//
//            override fun onFailure(call: Call<ApiResult>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}