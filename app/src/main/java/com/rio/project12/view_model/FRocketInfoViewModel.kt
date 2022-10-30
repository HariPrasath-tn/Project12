package com.rio.project12.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RocketInfoViewModelFactory(var application: Application) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RocketInfoViewModel::class.java)){
            return RocketInfoViewModel(application) as T
        }
        throw java.lang.IllegalArgumentException("Unable to create the instance of the view model")
    }
}