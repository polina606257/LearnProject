package com.example.recycler.di

import com.example.recycler.data.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

const val BASE_URL = "https://api.artic.edu/"