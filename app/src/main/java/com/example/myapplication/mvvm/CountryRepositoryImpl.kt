package com.example.myapplication.mvvm

import com.example.myapplication.network.ApiService
import com.example.myapplication.models.CountryDetails
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    CountryRepository {

    override suspend fun getCountriesList(): List<CountryDetails> {
        return apiService.getCountryList()
    }
}