package com.weatherforecastapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.weatherforecastapp.box.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(
                viewModelModule,
                apiModule,
                netModule,
                databaseModule,
                repositoryModule,
            ))
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}