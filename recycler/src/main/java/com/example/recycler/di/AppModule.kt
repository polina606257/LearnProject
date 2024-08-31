package com.example.recycler.di

import com.example.recycler.data.network.ArtworkRepositoryImpl
import com.example.recycler.ui.ArtworkRepository
import com.example.recycler.ui.ArtworkViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NetworkModule().provideRetrofit() }
    single { NetworkModule().provideApiService(get()) }
    single<ArtworkRepository> { ArtworkRepositoryImpl(get()) }
    viewModel { ArtworkViewModel(get()) }
}