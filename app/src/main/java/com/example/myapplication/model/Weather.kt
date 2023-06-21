package com.example.myapplication.model

import com.google.gson.annotations.SerializedName


data class Weather(
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val current: Current)


data class Location(
    @SerializedName("name")
    var name: String
)

data class Current(
    @SerializedName("temp_c") var temp_c: Float)
