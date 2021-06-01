package com.manuel28g.carsales.covidworlddata.repository

import com.manuel28g.carsales.covidworlddata.repository.api.CovidDataAPI

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CovidDataImpl @Inject constructor(var api:CovidDataAPI):CovidData {

    override fun getData(body:String) = flow{
            var response = api.getData(body).execute()
            emit(response.body())
    }

    override fun getCurrentData() = flow{
            var response = api.getData().execute()
            emit(response.body())
    }
}