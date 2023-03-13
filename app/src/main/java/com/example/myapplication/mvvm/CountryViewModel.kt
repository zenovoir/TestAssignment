package com.example.myapplication.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.CountryDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) :
    ViewModel() {

    var countryList = MutableLiveData<List<CountryDetails>>()

    suspend fun getCountriesList() {
        countryList.value = countryRepository.getCountriesList()
    }
}