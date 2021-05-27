package com.manuel28g.carsales.covidworlddata.ui.di

import com.manuel28g.carsales.covidworlddata.core.common.di.ActivityScope
import com.manuel28g.carsales.covidworlddata.ui.fragment.ErrorDataFragment
import com.manuel28g.carsales.covidworlddata.ui.fragment.HomeFragment
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(
    modules = [
        PresentationModule::class,
        RepositoryModule::class
    ]
)
@Singleton
interface CovidInfoPlatformComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: ErrorDataFragment)
}