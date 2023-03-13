package com.example.myapplication.dependancy_injections

import com.example.myapplication.network.ApiService
import com.example.myapplication.mvvm.CountryRepository
import com.example.myapplication.mvvm.CountryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun repositoryImplProvider(apiService: ApiService): CountryRepository {
        return CountryRepositoryImpl(apiService)
    }
}