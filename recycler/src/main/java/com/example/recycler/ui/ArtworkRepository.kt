package com.example.recycler.ui

import com.example.recycler.model.Artwork

interface ArtworkRepository {
    suspend fun getArtworks(page: Int, count: Int) : List<Artwork>
}