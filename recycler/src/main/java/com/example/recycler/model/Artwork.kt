package com.example.recycler.model

data class ArtworksResponse(
    val data: List<Artwork>
)

data class Artwork (
    val id: Long,
    val title: String
)