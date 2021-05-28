package com.manuel28g.carsales.covidworlddata.core.application

import android.app.Application
import android.content.Context
import au.com.carsales.basemodule.BaseModuleApplication
import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager
import com.manuel28g.carsales.covidworlddata.core.application.AppCovid.Companion.applicationComponent
import com.manuel28g.carsales.covidworlddata.module.ModuleLifeCycleManager
import com.manuel28g.carsales.covidworlddata.ui.di.CovidInfoPlatformComponent
import com.manuel28g.carsales.covidworlddata.ui.di.DaggerCovidInfoPlatformComponent

class AppCovid: BaseModuleApplication() {
    private lateinit var covidInfoPlatformComponent: CovidInfoPlatformComponent

    companion object{
        var applicationComponent: CovidInfoPlatformComponent? = null
    }

    override fun initLifeCycleManager(): BaseModuleLifeCycleManager {
       return ModuleLifeCycleManager(this)
    }

    override fun onCreate() {
        super.onCreate()
        //appComponent().inject(this)
        covidInfoPlatformComponent = DaggerCovidInfoPlatformComponent.builder().build()
    }

    fun getComponent() = covidInfoPlatformComponent
}


fun Context.appComponent(): CovidInfoPlatformComponent {
    return buildDagger(this.applicationContext)
}

private fun buildDagger(context: Context): CovidInfoPlatformComponent {
    if (applicationComponent == null) {

    }
    return applicationComponent!!
}