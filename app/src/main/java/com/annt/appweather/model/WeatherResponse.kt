package com.annt.appweather.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class City(
    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("coord")
    var coord: Coord? = null,

    @SerializedName("country")
    var country: String? = null,

    @SerializedName("population")
    var population: Int? = null,

    @SerializedName("timezone")
    var timezone: Int? = null
)
data class Coord(
    @SerializedName("lon")
    var lon: Double? = null,
    @SerializedName("lat")
    var lat: Double? = null
)

data class WeatherResponse(
    @SerializedName("city")
    var city: City? = null,
    @SerializedName("cod")
    var cod: String? = null,
    @SerializedName("message")
    var message: Double? = null,
    @SerializedName("cnt")
    var cnt: Int? = null,
    @SerializedName("list")
    var list: ArrayList<List>? = null
)

data class List(
    @SerializedName("dt")
    var dt: Long? = null,
    @SerializedName("sunrise")
    var sunrise: Int? = null,
    @SerializedName("sunset")
    var sunset: Int? = null,
    @SerializedName("temp")
    var temp: Temp? = null,
    @SerializedName("feels_like")
    var feelsLike: FeelsLike? = null,
    @SerializedName("pressure")
    var pressure: Int? = null,
    @SerializedName("humidity")
    var humidity: Int? = null,
    @SerializedName("weather")
    var weather: ArrayList<Weather>? = null,
    @SerializedName("speed")
    var speed: Double? = null,
    @SerializedName("deg")
    var deg: Int? = null,
    @SerializedName("gust")
    var gust: Double? = null,
    @SerializedName("clouds")
    var clouds: Int? = null,
    var pop: Double? = null,
    @SerializedName("rain")
    var rain: Double? = null
)

data class Temp(
    @SerializedName("day")
    var day: Double? = null,
    @SerializedName("min")
    var min: Double? = null,
    @SerializedName("max")
    var max: Double? = null,
    @SerializedName("night")
    var night: Double? = null,
    @SerializedName("eve")
    var eve: Double? = null,
    @SerializedName("morn")
    var morn: Double? = null
)

data class Weather(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("main")
    var main: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null
)

data class FeelsLike(
    @SerializedName("day")
    var day: Double? = null,
    @SerializedName("night")
    var night: Double? = null,
    @SerializedName("eve")
    var eve: Double? = null,
    @SerializedName("morn")
    var morn: Double? = null
)
