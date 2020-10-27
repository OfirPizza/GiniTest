package com.test.ginitest.application

import android.app.Application
import com.test.ginitest.network.retrofitModule
import com.test.ginitest.ui.numberModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class KoinStarter {

    var koinApplication: KoinApplication? = null

    fun start(application: Application) {
        koinApplication = getOrCreate(application)
    }

    private fun getOrCreate(application: Application): KoinApplication {
        return GlobalContext.getOrNull()?.apply {
            loadKoinModules(getModules())
        } ?: startKoinApplication(application)
    }

    private fun startKoinApplication(application: Application): KoinApplication {
        return startKoin {
            androidContext(application)
            androidLogger(Level.DEBUG)
            modules(getModules())
        }
    }

    private fun getModules(): List<Module> = listOf(
        retrofitModule,
        numberModule
    )
}