package com.example.myapplication.data

import com.example.myapplication.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    //https://api.weatherapi.com/v1/current.json?key=520916eb3f46442ca1c12926221402&q=visakhapatnam

    @GET("current.json")
    suspend fun getWeather(@Query("key")key: String, @Query("q")q: String): Weather
}




