package com.manuel28g.carsales.covidworlddata.viewmodel

import androidx.lifecycle.*

import com.manuel28g.carsales.covidworlddata.core.application.getDataService
import com.manuel28g.carsales.covidworlddata.core.application.isNetworkAvailable
import com.manuel28g.carsalesmobiletestdata.model.CovidInfo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CovidInfoViewModel @Inject constructor() : ViewModel() {

    private val mFormatter = SimpleDateFormat("yyyy-MM-dd")
    private val monthNameFormat = SimpleDateFormat("MMMM")
    private val numberFormat = NumberFormat.getInstance(Locale.GERMAN)
    private val mDayConsulted: MutableLiveData<Int> = MutableLiveData()
    private val mMonthConsulted: MutableLiveData<String> = MutableLiveData()
    private val mYearConsulted: MutableLiveData<Int> = MutableLiveData()
    private val mConfirmedCases: MutableLiveData<String> = MutableLiveData()
    private val mDeathPeople: MutableLiveData<String> = MutableLiveData()
    private val mIsApiResponse: MutableLiveData<Boolean> = MutableLiveData()
    private var mMinDate: Long? = null
    private var mMaxDate: Long? = null
    private var mError = MutableLiveData<Boolean>()

    init {
        getActualDate()
    }

    fun getMaxDate(): Long {
        if (mMaxDate == null) {
            var previousDay: Calendar = Calendar.getInstance().clone() as Calendar
            previousDay.add(Calendar.DAY_OF_MONTH, -1)
            mMaxDate = previousDay.timeInMillis
        }
        return mMaxDate!!
    }

    fun getMinDate(): Long {
        if (mMinDate == null) {
            var minDate: Calendar = Calendar.getInstance().clone() as Calendar
            minDate.add(Calendar.DAY_OF_MONTH, -1)
            minDate.set(Calendar.MONTH, 2)
            minDate.set(Calendar.YEAR, 2020)
            mMinDate = minDate.timeInMillis
        }
        return mMinDate!!
    }

    fun getActualDate() {
        mIsApiResponse.value = false
        viewModelScope.launch(Dispatchers.IO) {
            getDataService()?.getCurrentData()?.map {
                mapData(it)
            }?.catch {
                errorGettingData()
            }?.collect()

        }
    }

    private fun mapData(info: CovidInfo?) {
        val date: Calendar = Calendar.getInstance().clone() as Calendar
        date.time = mFormatter.parse(info?.data?.date)
        viewModelScope.launch(Dispatchers.Main) {
            mDayConsulted.value = date.get(Calendar.DAY_OF_MONTH)
            mYearConsulted.value = date.get(Calendar.YEAR)
            mMonthConsulted.value = monthNameFormat.format(date.time)
            mConfirmedCases.value = numberFormat.format(info?.data?.confirmed)
            mDeathPeople.value = numberFormat.format(info?.data?.deaths)
            mIsApiResponse.value = true
        }
    }

    fun isApiResponse(): LiveData<Boolean> {
        return mIsApiResponse
    }

    fun resetError() {
        mError.value = false
    }

    fun getDay(): LiveData<Int> {
        return mDayConsulted
    }

    fun getMonth(): LiveData<String> {
        return mMonthConsulted
    }

    fun getYear(): LiveData<Int> {
        return mYearConsulted
    }

    fun getConfirmedCases(): LiveData<String > {
        return mConfirmedCases
    }

    fun getTotalDeaths(): LiveData<String> {
        return mDeathPeople
    }

    fun andErrorOccurs(): LiveData<Boolean> {
        return mError
    }

    fun getData(body: String) {
        mIsApiResponse.value = false
        viewModelScope.launch(Dispatchers.IO) {
            getDataService()?.getData(body)?.map {
                mapData(it)
            }?.catch {
                errorGettingData()
            }?.collect()
        }
    }

    private fun errorGettingData(){
        viewModelScope.launch(Dispatchers.Main) {
            isNetworkAvailable()
            mError.value = true
        }
    }
}
