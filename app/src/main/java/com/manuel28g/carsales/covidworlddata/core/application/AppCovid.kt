package com.manuel28g.carsales.covidworlddata.core.application

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import au.com.carsales.basemodule.BaseModuleApplication
import au.com.carsales.basemodule.context
import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager
import com.manuel28g.carsales.covidworlddata.core.application.AppCovid.Companion.applicationComponent
import com.manuel28g.carsales.covidworlddata.module.ModuleLifeCycleManager
import com.manuel28g.carsales.covidworlddata.ui.di.CovidInfoApplicationModule
import com.manuel28g.carsales.covidworlddata.ui.di.CovidInfoPlatformComponent
import com.manuel28g.carsales.covidworlddata.ui.di.DaggerCovidInfoPlatformComponent
import com.manuel28g.carsalesmobiletestdata.service.CovidDataService
import com.manuel28g.carsalesmobiletestrouter.covidtestdata.getMobileTestData

open class AppCovid: BaseModuleApplication() {

    private lateinit var covidInfoPlatformComponent: CovidInfoPlatformComponent

    companion object {
        var applicationComponent: CovidInfoPlatformComponent? = null
    }

    override fun initLifeCycleManager(): BaseModuleLifeCycleManager {
        return ModuleLifeCycleManager(this)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent().inject(this)
        buildDagger(this)
    }

    fun getComponent() = covidInfoPlatformComponent
}


fun Context.appComponent(): CovidInfoPlatformComponent {
    return buildDagger(this.applicationContext)
}

fun Fragment.appComponent(): CovidInfoPlatformComponent {
    return buildDagger(this.requireContext().applicationContext)
}

fun ViewModel.getDataService():CovidDataService?{
    return context?.getMobileTestData()
}

private fun buildDagger(context: Context): CovidInfoPlatformComponent {
    if (applicationComponent == null) {
        applicationComponent = DaggerCovidInfoPlatformComponent
            .builder()
            .covidInfoApplicationModule(CovidInfoApplicationModule(context as AppCovid))
            .build()
    }
    return applicationComponent!!
}