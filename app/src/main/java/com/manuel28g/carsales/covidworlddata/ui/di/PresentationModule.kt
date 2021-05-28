package com.manuel28g.carsales.covidworlddata.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuel28g.carsales.covidworlddata.core.common.di.AppCoroutineExecutionThread
import com.manuel28g.carsales.covidworlddata.core.common.di.CoroutineExecutionThread
import com.manuel28g.carsales.covidworlddata.core.common.di.ViewModelFactory
import com.manuel28g.carsales.covidworlddata.core.common.di.ViewModelKey
import com.manuel28g.carsales.covidworlddata.viewmodel.CovidInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindCoroutineExecutionThread(executionThread: AppCoroutineExecutionThread): CoroutineExecutionThread

    @Binds
    @IntoMap
    @ViewModelKey(CovidInfoViewModel::class)
    abstract fun bindCovidInfoViewModel(viewModel: CovidInfoViewModel): ViewModel


    @Module
    companion object {
        @Provides
        fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
    }

}