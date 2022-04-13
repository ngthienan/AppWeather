package com.annt.appweather

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annt.appweather.api.WeatherService
import com.annt.appweather.db.WeatherData
import com.annt.appweather.model.WeatherDataModel
import com.annt.igitproject.model.WeatherAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var weatherService: WeatherService
    var adapter : WeatherAdapter? = null
    var weatherList : ArrayList<WeatherDataModel> = ArrayList()
    var weatherListForDb : ArrayList<WeatherData> = ArrayList()
    lateinit var editText: EditText
    lateinit var rcvWeather : RecyclerView
    lateinit var btnSearch : Button
    lateinit var btnSearchDb : Button
    @SuppressLint("NewApi", "SimpleDateFotmat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edtNameCity)
        rcvWeather = findViewById(R.id.rcv_weather)
        btnSearch = findViewById(R.id.btnGetWeather)
        btnSearchDb = findViewById(R.id.btnSearchdB)

        btnSearchDb.setOnClickListener(this)
        btnSearch.setOnClickListener(this)

        rcvWeather.layoutManager = LinearLayoutManager(this)
        rcvWeather.setHasFixedSize(true)
        rcvWeather.itemAnimator = DefaultItemAnimator()
        rcvWeather.addItemDecoration(
            DividerItemDecoration(
                rcvWeather.context,
            DividerItemDecoration.VERTICAL
            )
        )

        weatherService = WeatherService.create()
        adapter = WeatherAdapter(weatherList)
        rcvWeather.adapter = adapter

        }

    override fun onClick(view: View?) {
        Log.d("MainActivity" , "onClick ${view?.id}")
        when(view?.id) {
            R.id.btnGetWeather ->{
                var textSearch = editText.text
                searchWeather(textSearch.toString())
            }
            R.id.btnSearchdB ->{
                val intent = Intent(this,SearchdBActivity:: class.java)
                startActivity(intent)
            }
        }
    }
    @SuppressLint("NewApi")
    fun searchWeather(textSearch : String = "SaiGon", numberOfDay : Int = 7){

        GlobalScope.launch(Dispatchers.IO) {
            val response = weatherService.getUser(textSearch, numberOfDay,"60c6fbeb4b93ac653c492ba806fc346d").execute()

            val body = response.body()
            val listWeather = body?.list
            withContext(Dispatchers.Main){
                weatherList.clear()
                adapter?.notifyDataSetChanged()
            }
            listWeather?.forEach { item ->
                val timeD = item.dt?.times(1000)?.let { Date(it) }
                val sdf = SimpleDateFormat("EEEE, dd/MMM/yyyy", Locale("en"))
                val dateFormatted = sdf.format(timeD)
                var weatherDateModel = WeatherDataModel(
                    "Date : $dateFormatted",
                    "Average temperature : ${item.temp?.eve}",
                    "Pressure : ${item.pressure}",
                    "Humidity : ${item.humidity}",
                    "Description :${
                        item.weather?.get(
                            0
                        )?.description
                    }"
                )
                var weatherData = WeatherData(
                    "Date : $dateFormatted",
                    "Average temperature : ${item.temp?.eve}",
                    "Pressure : ${item.pressure}",
                    "Humidity : ${item.humidity}",
                    "Description :${
                        item.weather?.get(
                            0
                        )?.description
                    }")
                weatherListForDb.add(weatherData)
                weatherList.add(weatherDateModel)
            }
            MyApplication.instance.database.productDao().deleteAll()
            MyApplication.instance.database.productDao().insertAll(weatherListForDb)
            weatherListForDb.clear()
            withContext(Dispatchers.Main){
                Log.d("MainActivity", "Update RCV: ${weatherList.size}")
                //adapter!!.notifyDataSetChanged()
                adapter!!.submitList(weatherList)
            }
        }
        Toast.makeText(this, "Searching weather at $textSearch", Toast.LENGTH_LONG).show()
    }
        }
