package com.example.myapplication.dependancy_injections

import com.example.myapplication.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun gson():Gson{
        return GsonBuilder()
            .setLenient().create()
    }

    @Provides
    @Singleton
    fun retrofitProvider(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/keeguon/2310008/raw/bdc2ce1c1e3f28f9cab5b4393c7549f38361be4e/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun apiServiceProviders(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}