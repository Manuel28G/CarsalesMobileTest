package com.manuel28g.carsales.covidworlddata.core.common.di

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineExecutionThread {

    fun uiThread(): CoroutineDispatcher

    fun ioThread(): CoroutineDispatcher

}