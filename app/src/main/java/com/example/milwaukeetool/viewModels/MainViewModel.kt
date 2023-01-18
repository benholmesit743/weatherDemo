package com.example.milwaukeetool.viewModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.milwaukeetool.data.CapitalData
import com.example.milwaukeetool.repository.AppRepository
import com.example.milwaukeetool.retrofit.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("CheckResult")
class MainViewModel(private val apiService: ApiService, private val repository: AppRepository): ViewModel() {
    private var disposable = CompositeDisposable()
    var currentItem: CapitalData? = null

    fun getFiveDayForecast() {
        val dates = getStartAndEndDates()
        disposable.add(
            apiService.getWeather(latitude = 34.00, longitude = -81.03,
                startDate = dates[0], endDate = dates[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.timezone != null) {
                        Log.e("Not null", "Not null")
                    }
                }, {
                    it.printStackTrace()
                }))
    }

    private fun Date.getFormattedString(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return formatter.format(this)
    }

    private fun getStartAndEndDates(): ArrayList<String> {
        val calendar = Calendar.getInstance()
        val today = calendar.time.getFormattedString()
        calendar.add(Calendar.DATE, 4)
        val fifthDay = calendar.time.getFormattedString()
        return arrayListOf(today, fifthDay)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}