package com.davidbaudais.myrecipeapp

import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create()).build()

    val recipieService = retrofit.create(ApiService::class.java)

interface ApiService{
    // gets a http request to get the file from the web server
    @GET("categories.php")

    // suspends the ApiService function just enough for the @get
    // to finish getting the data from the internet
    suspend fun getCategories(): CategoriesResponse
}