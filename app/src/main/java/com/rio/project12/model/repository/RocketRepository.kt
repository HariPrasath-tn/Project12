package com.rio.project12.model.repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.Transformations
//import com.rio.project12.model.database.DRocketHistory
//import com.rio.project12.model.network.RocketHistoryApiClient
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import retrofit2.await
//
///**
// * CharacterRepository is an user defined repository class (plain class) that decides when to fetch
// * data from which data source and when to refresh the offline cache. In this way data process logic
// * is hidden from the viewModel and we can keep small and maintainable. This repository class helps
// * us to keep app layers separate from one another
// */
//class RocketRepository (private val database: CharacterDatabase) {
//
//    /**
//     * characters is a liveData variable that holds a list of dataclass AnimeCharacter objects
//     *
//     * Value Assigning methodology:
//     *      -> database.characterDAO.getAllCharacter fetches all the character from the database with
//     *      respect to the query given.
//     *      -> Then each records are mapped to the object of the of the
//     *      data class AnimeCharacter, then assigned to the variable
//     *
//     */
//    val characters: LiveData<List<DRocketHistory>> = Transformations.map(database.characterDAO.getAllCharacter()) {
//        it.asDomainModel()
//    }
//
//    /**
//     * refreshCharacter() is a method that runs in separate thread (thread other than main) that
//     * update the database
//     *
//     *
//     * it update all the values in the database
//     *      -> insert if the data doesn't exist already
//     *      -> replace if the data already exists
//     *
//     */
//    suspend fun refreshCharacter() {
//        withContext(Dispatchers.IO) {
//            try {
//                val characters = RocketHistoryApiClient.apiService.fetchCharacters().await()
//                database.characterDAO.insertAll(*characters.asDatabaseModel())
//            }catch (ignored:Exception){}
//        }
//    }
//}