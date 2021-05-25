package com.manuel28g.carsales.covidworlddata.ui.di

import com.manuel28g.carsales.covidworlddata.helpers.RetrofitHelper
import com.manuel28g.carsales.covidworlddata.repository.CovidData
import com.manuel28g.carsales.covidworlddata.repository.CovidDataImpl
import com.manuel28g.carsales.covidworlddata.repository.api.CovidDataAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCovidData(): CovidDataAPI {
        return RetrofitHelper().getInstance()
    }

    @Singleton
    @Provides
    fun bindRepository(covidDataAPI: CovidDataAPI): CovidData {
        return CovidDataImpl(covidDataAPI)
    }

}