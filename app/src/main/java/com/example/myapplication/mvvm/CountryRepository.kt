package com.example.myapplication.mvvm

import com.example.myapplication.models.CountryDetails

interface CountryRepository {
    suspend fun getCountriesList(): List<CountryDetails>
}