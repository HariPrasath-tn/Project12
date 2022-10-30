package com.rio.project12.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rio.project12.model.database.DRocketHistory
import com.rio.project12.model.database.RocketHistoryDatabase
import com.rio.project12.model.repository.RocketRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RocketListViewModel(application: Application) :ViewModel(){
    /**
     * instance of the class RocketDatabase
     */
    // creating the instance of the database
    private val database = RocketHistoryDatabase.getINSTANCE(application)

    /**
     * instance of the  class rocket repository
     */
    // creating the instance of the class RocketRepository
    private val rocketRepository = RocketRepository.getInstance(this, database)


    // history fetched from the database
    val history :LiveData<List<DRocketHistory>> = rocketRepository.history

    init{
        // initializing the coroutine to refresh the database to update the database
        viewModelScope.launch{
            rocketRepository.refreshDb()
        }
    }
}