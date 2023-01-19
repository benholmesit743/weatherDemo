package com.example.milwaukeetool.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.milwaukeetool.data.CapitalCoordinates
import com.example.milwaukeetool.data.CapitalData
import com.example.milwaukeetool.data.getState
import com.example.milwaukeetool.data.toCapitalData
import com.example.milwaukeetool.repository.AppRepository
import com.example.milwaukeetool.retrofit.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(private val apiService: ApiService, private val repository: AppRepository): ViewModel() {
    private var disposable = CompositeDisposable()
    var currentItem: CapitalData? = null
    lateinit var forecastData: LiveData<CapitalData>

    fun getFiveDayForecastFromApi() {
        val dates = getStartAndEndDates()
        currentItem?.let { capital ->
            disposable.add(
                apiService.getWeather(latitude = capital.latitude, longitude = capital.longitude,
                    startDate = dates[0], endDate = dates[1])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        addToRepo(
                            it.toCapitalData(capital.state, capital.capital, capital.latitude, capital.longitude)
                        )
                    }, {
                        it.printStackTrace()
                    }))
        }
    }

    fun getFiveDayForecastFromDatabase() {
        currentItem?.let {
            forecastData = repository.getById(it.uid).asLiveData()
        }
    }

    private fun addToRepo(capital: CapitalData) {
        viewModelScope.launch {
            repository.insert(capital)
        }
    }

    fun getDataForStartFragment(): List<CapitalData> {
        val result: ArrayList<CapitalData> = ArrayList()
        for (item in CapitalCoordinates.values()) {
            result.add(
                CapitalData(
                    uid = "${item.getState()}_${item.capital}",
                    state = item.getState(),
                    capital = item.capital,
                    latitude = item.lat,
                    longitude = item.lon,
                    timeStamp = "${System.currentTimeMillis()}"
                )
            )
        }
        return result
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