package com.example.recycler.di

import com.example.recycler.data.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.artic.edu/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}