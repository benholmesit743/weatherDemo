package com.example.umo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.umo.data.ZipCode
import com.example.umo.data.toUnits
import com.example.umo.data.toZipCode
import com.example.umo.repository.AppRepository
import com.example.umo.retrofit.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService, private val repository: AppRepository): ViewModel() {
    private var disposable = CompositeDisposable()
    var currentItem: ZipCode? = null
    lateinit var forecastData: LiveData<ZipCode>
    val startData: LiveData<List<ZipCode>> = repository.getAll().asLiveData()

    fun getCurrentTemperatureFromApi() {
        currentItem?.let { zip ->
            disposable.add(
                apiService.getWeather(location = "$zip.zipCode%20US", units = zip.unit.toUnits())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        addToRepo(
                            it.toZipCode(zip.zipCode, zip.unit)
                        )
                    }, {
                        it.printStackTrace()
                    }))
        }
    }

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