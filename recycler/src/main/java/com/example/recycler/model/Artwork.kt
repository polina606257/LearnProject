package com.example.recycler.model

data class ArtworksResponse(
    val data: List<Artwork>
)

data class Artwork (
    val id: Long,
    val title: String,
    val thumbnail: ArtworkImage
): BaseItem()

data class ArtworkImage(
    val lqip: String
)