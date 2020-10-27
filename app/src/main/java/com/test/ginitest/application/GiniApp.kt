package com.test.ginitest.application

import android.app.Application

class GiniApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        KoinStarter().start(this)
    }
}