package com.manuel28g.carsales.covidworlddata.ui.di

import au.com.carsales.basemodule.dependency.component.BaseApplicationComponent
import com.manuel28g.carsales.covidworlddata.core.common.di.ActivityScope
import com.manuel28g.carsales.covidworlddata.ui.fragment.ErrorDataFragment
import com.manuel28g.carsales.covidworlddata.ui.fragment.HomeFragment

import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(
    modules = [
        PresentationModule::class,
        CovidInfoApplicationModule::class
    ]
)
@Singleton
interface CovidInfoPlatformComponent: BaseApplicationComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: ErrorDataFragment)
}