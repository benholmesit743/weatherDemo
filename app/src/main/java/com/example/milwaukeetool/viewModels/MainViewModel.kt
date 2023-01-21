package com.example.milwaukeetool.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.milwaukeetool.data.CapitalData
import com.example.milwaukeetool.data.toCapitalData
import com.example.milwaukeetool.repository.AppRepository
import com.example.milwaukeetool.retrofit.ApiService
import com.example.milwaukeetool.utilities.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService, private val repository: AppRepository): ViewModel() {
    private var disposable = CompositeDisposable()
    var currentItem: CapitalData? = null
    lateinit var forecastData: LiveData<CapitalData>
    val startData: LiveData<List<CapitalData>> = repository.getAll().asLiveData()

    fun getFiveDayForecastFromApi() {
        val dates = Utility.getStartAndEndDates()
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

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}