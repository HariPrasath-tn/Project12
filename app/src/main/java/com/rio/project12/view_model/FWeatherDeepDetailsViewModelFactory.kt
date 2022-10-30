package com.rio.project12.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FWeatherDeepDetailsViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherDeepDetailsViewModel::class.java)){
            return WeatherDeepDetailsViewModel(application) as T
        }
        throw IllegalArgumentException("No Argument required but found one")
    }
}