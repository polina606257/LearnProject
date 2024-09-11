package com.example.recycler.data.network

import com.example.recycler.model.ArtworksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/artworks")
    suspend fun getArtworks(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ArtworksResponse
}