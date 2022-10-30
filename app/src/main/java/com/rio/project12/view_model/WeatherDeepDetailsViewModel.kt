package com.rio.project12.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rio.project12.model.network.weather.Weather
import com.rio.project12.model.repository.WeatherRepository
import io.reactivex.rxjava3.schedulers.Schedulers.io
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher

class WeatherDeepDetailsViewModel(val application: Application): ViewModel(){
    private var _airQuality = MutableLiveData<String>()
     var _airQualityReading = MutableLiveData<String>()
    private var _timeZone = MutableLiveData<String>()
    private var _place = MutableLiveData<String>()
    private var _image = MutableLiveData<String>()
    private var _temperature = MutableLiveData<String>()
    private var _description = MutableLiveData<String>()
    private var _windData = MutableLiveData<String>()

    val airQuality:LiveData<String>
        get() = _airQuality
    val airQualityReading:LiveData<String>
        get() = _airQualityReading
    val timeZone:LiveData<String>
        get() = _timeZone
    val place:LiveData<String>
        get() = _place
    val image:LiveData<String>
        get() = _image
    val temperature:LiveData<String>
        get() = _temperature
    val description:LiveData<String>
        get() = _description
    val windData:LiveData<String>
        get() = _windData

    private var weatherRepository = WeatherRepository.getInstance(this)

    private lateinit var weather:Weather

    init {
        viewModelScope.launch {
            weather = weatherRepository.fetchWeatherData("12.8315981", "80.0518339")
            _airQualityReading.value = (weather.data[0].aqi.toString())

            _airQuality.value = "(${
                if(airQualityReading.value?.toDouble()!! > 100.0){
                    "harmful"
                }else if(airQualityReading.value?.toDouble()!! > 60.0){
                    "Moderate"
                }else{
                    "Normal"
                }
            })"
                _image.value = "https://www.weatherbit.io/static/img/icons/r01d.png"//weather.data[0].weather.icon
            _timeZone.value = weather.data[0].timezone
            _place.value = weather.data[0].city_name
                _temperature.value = "${weather.data[0].app_temp}°C"
            _description.value = weather.data[0].weather.description
            _windData.value = "${weather.data[0].wind_dir}°" + "(${weather.data[0].wind_spd}m/s)"

        }
    }


}