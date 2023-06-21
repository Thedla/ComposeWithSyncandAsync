package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Weather
import com.example.myapplication.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    private val _tag = "WeatherViewModel"

    val weatherList: MutableLiveData<List<Weather>> = MutableLiveData(emptyList())


    fun fetchWeatherDataAsyncThenSync(group1: List<String>, group2: List<String>) =
        viewModelScope.launch {

            Log.i(_tag, "------Async call Start----------")
            val deferred = group1.map { city ->
                async {
                    repository.getWeather(city)
                }
            }

            val weatherDataAsync = deferred.map { it.await() }
            updateWeatherList(weatherDataAsync)
            Log.i(_tag, "------Async call End----------")


            Log.i(_tag, "------synchronous call Start----------")
            val weatherDataSync = group2.map { repository.getWeather(it) }
            updateWeatherList(weatherDataSync)
            Log.i(_tag, "------synchronous call End----------")
        }

    private fun updateWeatherList(newData: List<Weather>) {
        val currentData = weatherList.value.orEmpty()
        val updatedData = currentData + newData
        weatherList.postValue(updatedData)
    }

}




