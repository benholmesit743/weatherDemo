package com.example.milwaukeetool.viewModels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.milwaukeetool.data.CapitalData
import com.example.milwaukeetool.retrofit.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class MainViewModel(private val apiService: ApiService): ViewModel() {

    private var disposable = CompositeDisposable()
    var currentItem: CapitalData? = null

    fun getData() {
        disposable.add(apiService.getWeather()
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

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}