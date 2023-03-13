package com.example.myapplication.network

import com.example.myapplication.models.CountryDetails
import retrofit2.http.GET


interface ApiService {
    @GET("countries.json")
    suspend fun getCountryList(): List<CountryDetails>
}