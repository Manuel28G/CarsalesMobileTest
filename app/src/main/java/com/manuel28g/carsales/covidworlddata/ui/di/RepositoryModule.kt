package com.manuel28g.carsales.covidworlddata.ui.di

import com.manuel28g.carsales.covidworlddata.repository.CovidData
import com.manuel28g.carsales.covidworlddata.repository.CovidDataImpl
import com.manuel28g.carsales.covidworlddata.repository.api.CovidDataAPI
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(covidRepository:CovidDataImpl): CovidData

//    @Module
//    companion object{
//        @Provides
//        @JvmStatic
//        fun provideRestApi(): CovidDataAPI {
//            return provideRestApi()
//        }
//    }

}