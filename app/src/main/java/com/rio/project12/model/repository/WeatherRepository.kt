package com.rio.project12.model.repository

import androidx.lifecycle.ViewModel
import com.rio.project12.exception.UnAuthorizedAccessException
import com.rio.project12.model.network.WeatherApiClient
import com.rio.project12.model.network.weather.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class WeatherRepository private constructor(){
    /**
     * @param obj key,
     * static method to get the instance of the repository weather
     */
    /*
     * obj is the instance of the class that is asking for the instance of
     * the class WeatherRepository
     */
    companion object{
        fun getInstance(obj:Any):WeatherRepository{
            if(obj is ViewModel){
                return WeatherRepository()
            }
            throw UnAuthorizedAccessException("Access Denied")
        }
    }

    /**
     * suspended method fetchWeatherData fetch the current weather data
     */
    suspend fun fetchWeatherData(lat:String, lon:String): Weather {
        return withContext(Dispatchers.IO) {
                WeatherApiClient.weatherApiService.fetchAll(lat, lon).await()
        }
    }
}