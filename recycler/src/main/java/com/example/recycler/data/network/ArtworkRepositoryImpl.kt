package com.example.recycler.data.network

import android.util.Log
import com.example.recycler.model.Artwork
import com.example.recycler.ui.ArtworkRepository

class ArtworkRepositoryImpl(val apiService: ApiService) : ArtworkRepository {
    override suspend fun getArtworks(page: Int, count: Int): List<Artwork> {
        return apiService.getArtworks(page, count).data
    }

    override suspend fun getPicture(identifier: String): String {
        return try {
            apiService.getPicture(identifier)
        } catch (e: Exception) {
            e.message.toString()
        }
    }
}