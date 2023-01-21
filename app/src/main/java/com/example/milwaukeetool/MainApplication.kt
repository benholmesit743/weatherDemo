package com.example.milwaukeetool

import android.app.Application
import androidx.room.Room
import androidx.work.*
import com.example.milwaukeetool.database.AppDatabase
import com.example.milwaukeetool.repository.AppRepository
import com.example.milwaukeetool.retrofit.ApiService
import com.example.milwaukeetool.viewModels.MainViewModel
import com.example.milwaukeetool.worker.DownloadManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication: Application(), Configuration.Provider {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
        downloadData()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
    }
    private fun downloadData() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequest.Builder(DownloadManager::class.java)
            .setConstraints(constraints)
            .build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(request)
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