package com.manuel28g.carsales.covidworlddata.module

import android.app.Application
import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager

internal class ModuleLifeCycleManager(application: Application):
    BaseModuleLifeCycleManager(application) {
    init {
        //moduleLifeCycleList.add(CovidDataRouterLifeCycle(application))
        //moduleLifeCycleList.add(CovidDataLifeCycle(application))
    }
}