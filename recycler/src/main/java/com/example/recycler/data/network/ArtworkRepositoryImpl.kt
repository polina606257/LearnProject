package com.example.recycler.data.network

import com.example.recycler.model.Artwork
import com.example.recycler.ui.ArtworkRepository

class ArtworkRepositoryImpl(val apiService: ApiService) : ArtworkRepository {
    override suspend fun getArtworks(page: Int, count: Int): List<Artwork> {
        return apiService.getArtworks(page, count).data
    }
}