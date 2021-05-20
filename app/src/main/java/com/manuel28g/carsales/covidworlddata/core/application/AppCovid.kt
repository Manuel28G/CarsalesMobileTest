package com.manuel28g.carsales.covidworlddata.core.application

import android.app.Application
import com.manuel28g.carsales.covidworlddata.ui.di.CovidInfoPlatformComponent
import com.manuel28g.carsales.covidworlddata.ui.di.DaggerCovidInfoPlatformComponent

class AppCovid: Application() {
    private lateinit var covidInfoPlatformComponent: CovidInfoPlatformComponent

    override fun onCreate() {
        super.onCreate()
        covidInfoPlatformComponent = DaggerCovidInfoPlatformComponent.builder().build()
    }

    fun getComponent() = covidInfoPlatformComponent
}