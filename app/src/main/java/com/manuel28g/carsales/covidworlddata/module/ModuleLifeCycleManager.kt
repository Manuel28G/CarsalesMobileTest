package com.manuel28g.carsales.covidworlddata.module

import android.app.Application
import au.com.carsales.basemodule.router.BaseModuleLifeCycleManager
import com.manuel28g.carsalesmobiletestrouter.covidtestdata.lifecycle.CovidDataRouterLifeCycle

class ModuleLifeCycleManager(application: Application):
    BaseModuleLifeCycleManager(application){
    init {
        moduleLifeCycleList.add(CovidDataRouterLifeCycle(application))
    }
}