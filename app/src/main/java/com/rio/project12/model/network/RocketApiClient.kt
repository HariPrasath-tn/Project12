package com.rio.project12.model.network

import com.rio.project12.model.network.rocket.DRocket
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RocketApiClient {
    /**
     * base url
     */
    private const val BASE_URL = "https://api.spacexdata.com/v5/"

    /**
     * creating the instance of the retrofit as lazy
     */
    private val retrofit:Retrofit by lazy{
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * creating the object of the ApiService as lazy
     */
    val apiService:ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}

/**
 * Below object class lets create an interface to define how Retrofit talks to the service using the GetMethod
 */
interface ApiService{
    /**
     * @fetchALL is the data fetching method that is annotated with
     * @GET annotation passing the launches as path
     */
    @GET("launches")
    fun fetchAll(): Call<List<DRocket>>
}