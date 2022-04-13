package com.annt.appweather.api

import com.annt.appweather.model.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/forecast/daily")
    fun getUser(
        @Query("q") city: String,
        @Query("cnt") numberofday: Int,
        @Query("appid") appId: String
    )
            : Call<WeatherResponse>

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/"

        fun create(): WeatherService {

            val logging  = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WeatherService:: class.java)
        }

    }
}