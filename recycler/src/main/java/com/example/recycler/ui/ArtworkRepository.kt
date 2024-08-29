package com.example.recycler.ui

import com.example.recycler.model.Artwork
import org.intellij.lang.annotations.Identifier

interface ArtworkRepository {
    suspend fun getArtworks(page: Int, count: Int) : List<Artwork>
    suspend fun getPicture(identifier: String) : String
}