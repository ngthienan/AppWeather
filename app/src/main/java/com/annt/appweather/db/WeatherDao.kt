package com.annt.igitproject.db

import androidx.room.*
import com.annt.appweather.db.WeatherData
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherData")
    fun getAll(): List<WeatherData>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<WeatherData>)

    @Query("DELETE FROM WeatherData")
    suspend fun deleteAll()

}