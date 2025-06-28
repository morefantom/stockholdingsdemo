package com.prathamesh.stockholdingsdemo.ui

import android.app.Application
import com.prathamesh.stockholdingsdemo.ui.di.AppComponent
import com.prathamesh.stockholdingsdemo.ui.di.DaggerAppComponent

class StockHoldingsDemoApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .build()
    }

}