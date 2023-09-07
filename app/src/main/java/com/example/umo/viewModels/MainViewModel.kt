package com.example.umo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.umo.data.ZipCode
import com.example.umo.repository.AppRepository
import com.example.umo.retrofit.ApiService
import com.example.umo.utilities.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService, private val repository: AppRepository): ViewModel() {
    private var disposable = CompositeDisposable()
    var currentItem: ZipCode? = null
    lateinit var forecastData: LiveData<ZipCode>
    val startData: LiveData<List<ZipCode>> = repository.getAll().asLiveData()

//    fun getCurrentTemperatureFromApi() {
//        val dates = Utility.getStartAndEndDates()
//        currentItem?.let { capital ->
//            disposable.add(
//                apiService.getWeather(latitude = capital.latitude, longitude = capital.longitude,
//                    startDate = dates[0], endDate = dates[1])
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        addToRepo(
//                            it.toCapitalData(capital.state, capital.capital, capital.latitude, capital.longitude)
//                        )
//                    }, {
//                        it.printStackTrace()
//                    }))
//        }
//    }

    fun getCurrentTemperatureFromDatabase() {
        currentItem?.let {
            forecastData = repository.getById(it.zipCode).asLiveData()
        }
    }

    fun addToRepo(zipCode: ZipCode) {
        viewModelScope.launch {
            repository.insert(zipCode)
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}