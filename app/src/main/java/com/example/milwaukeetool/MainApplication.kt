package com.example.milwaukeetool

import android.app.Application
import androidx.room.Room
import com.example.milwaukeetool.database.AppDatabase
import com.example.milwaukeetool.repository.AppRepository
import com.example.milwaukeetool.retrofit.ApiService
import com.example.milwaukeetool.viewModels.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }

    private val appModule = module {
        single {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        }
        single { ApiService.create(get())}
        single {
            val database = Room.databaseBuilder(
                            applicationContext,
                            AppDatabase::class.java, "App-Database"
                            ).build()
            database.dao()
        }
        single { AppRepository(get()) }
        viewModel { MainViewModel(get(), get()) }
    }
}