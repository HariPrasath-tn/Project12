package com.rio.project12.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rio.project12.exception.UnAuthorizedAccessException
import com.rio.project12.model.database.DRocketHistory
import com.rio.project12.model.database.RocketHistoryDatabase
import com.rio.project12.model.database.asDomainModel
import com.rio.project12.model.network.RocketApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await


/**
 * RocketRepository is an user defined repository class (plain class) that decides when to fetch
 * data from which data source and when to refresh the offline cache. In this way data process logic
 * is hidden from the viewModel and we can keep small and maintainable. This repository class helps
 * us to keep app layers separate from one another
 */
class RocketRepository private constructor(private val database: RocketHistoryDatabase) {

    companion object{

        /**
         * @param database instance of the database,
         * @param obj key,
         * static method to get the instance of the repository rocket
         */
        /*
         * obj is the instance of the class that is asking for the instance of
         * the class RocketRepository
         */
        fun getInstance(obj:Any, database:Any):RocketRepository{
            if(obj is ViewModel){
                return RocketRepository(database as RocketHistoryDatabase)
            }
            throw UnAuthorizedAccessException("Access Denied")
        }
    }

    /**
     * rocketHistory is a liveData variable that holds a list of dataclass AnimeCharacter objects
     *
     * Value Assigning methodology:
     *      -> [database.getHistoryDao().getAllCharacter] fetches all the history from the database with
     *      respect to the query given.
     *
     */
    val history: LiveData<List<DRocketHistory>> = database.getHistoryDao().getAll()

    /**
     * refreshCharacter() is a method that runs in separate thread (thread other than main) that
     * update the database
     *
     *
     * it update all the values in the database
     *      -> insert if the data doesn't exist already
     *      -> replace if the data already exists
     *
     */
    suspend fun refreshDb() {
        withContext(Dispatchers.IO) {
            try {
                val history = RocketApiClient.apiService.fetchAll().await()
                database.getHistoryDao().insertAll(*history.asDomainModel())
            }catch (ignored:Exception){}
        }
    }
}