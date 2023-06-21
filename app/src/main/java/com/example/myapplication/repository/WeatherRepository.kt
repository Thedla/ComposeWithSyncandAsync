package com.example.myapplication.repository

import com.example.myapplication.constants.NetworkConstants.WEATHER_APIKEY
import com.example.myapplication.data.WeatherService
import com.example.myapplication.model.Weather
import javax.inject.Inject


/*interface WeatherRepository {
    suspend fun fetchWeatherDataAsync(cities: List<String>): List<Weather>
    suspend fun fetchWeatherData(
        cities: String
    )

    fun fetchWeatherData1(cities: String,callback: (Weather?, Throwable?) -> Unit)
}

class WeatherRepositoryImpl(private val apiService: WeatherService) : WeatherRepository {

    override suspend fun fetchWeatherDataAsync(cities: List<String>): List<Weather> {
        val asyncWeatherData = mutableListOf<Weather>()

        *//*for (city in cities) {
            val response = apiService.getWeather(WEATHER_APIKEY, city)
            if (response.isSuccessful) {
                val weatherData = response.body() as? Weather
                weatherData?.let {
                    asyncWeatherData.add(it)
                }
            } else {
                throw Exception("Error fetching weather data for $city")
            }

        }*//*

        return asyncWeatherData
    }

    override suspend fun fetchWeatherData(
        city: String
    ) {
        val response = apiService.getWeather(WEATHER_APIKEY, city)
        if (response.isSuccessful) {
            val weatherData = response.body() as? Weather
            weatherData?.let {
                Log.i("WeatherRepository", "city response is $city success - ${it}")
            }
        } else {
            throw Exception("Error fetching weather data for $city")
        }

        *//*response.enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {

                Log.i("WeatherRepository", "city response is $city success - ${response.body()}")

            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.i("WeatherRepository", "city response is $city fail - ${t}")
            }

        })*//*


    }



    override fun fetchWeatherData1(city: String, callback: (Weather?, Throwable?) -> Unit) {
        val call = apiService.getWeather(WEATHER_APIKEY, city)
        call.enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    callback(weather, null)
                } else {
                    val error = Throwable("Failed to fetch weather data for $city: ${response.code()}")
                    callback(null, error)
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                callback(null, t)
            }
        })
    }
}*/

class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {
    suspend fun getWeather(city: String): Weather {
        return weatherService.getWeather(WEATHER_APIKEY, city)
    }
}

