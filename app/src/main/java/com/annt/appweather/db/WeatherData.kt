package com.annt.appweather.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class WeatherData (
    @ColumnInfo(name = "Date")
    var dt: String,

    @ColumnInfo(name = "Ave")
    var eve: String,

    @ColumnInfo(name = "Pressure")
    var pressure: String,

    @ColumnInfo(name = "Humidity")
    var humidity: String,

    @ColumnInfo(name = "Description")
    var description: String

)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var WeatherId: Long = 0


}