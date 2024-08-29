package com.example.recycler.data.network

import com.example.recycler.model.ArtworksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.Result

interface ApiService {
    @GET("api/v1/artworks")
    suspend fun getArtworks(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ArtworksResponse

    @GET("iiif/2/{identifier}/full/843,/0/default.jpg")
    suspend fun getPicture(@Path("identifier") identifier: String): String
}