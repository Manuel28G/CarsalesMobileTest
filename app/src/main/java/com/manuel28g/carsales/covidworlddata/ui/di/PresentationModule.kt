package com.manuel28g.carsales.covidworlddata.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuel28g.carsales.covidworlddata.core.common.di.ViewModelFactory
import com.manuel28g.carsales.covidworlddata.core.common.di.ViewModelKey
import com.manuel28g.carsales.covidworlddata.helpers.RetrofitHelper
import com.manuel28g.carsales.covidworlddata.viewmodel.CovidInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CovidInfoViewModel::class)
    abstract fun bindCovidInfoViewModel(viewModel: CovidInfoViewModel): ViewModel


    @Module
    companion object {
        @Provides
        fun provideHelper(): RetrofitHelper {
            return RetrofitHelper()
        }
    }

}