package com.example.recycler.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recycler.data.network.Result
import com.example.recycler.model.Artwork
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtworkViewModel(val artworkRepository: ArtworkRepository) : ViewModel() {
    private val _artworkState = MutableStateFlow<Result<List<Artwork>>>(Result.Loading)
    val artworkState: StateFlow<Result<List<Artwork>>>
        get() = _artworkState

    fun fetchData() {
        viewModelScope.launch {
            try {
                val artworkWithImage = mutableListOf<Artwork>()
                val artworkList = async {  artworkRepository.getArtworks(1, 5) }
                for (artwork in artworkList.await()) {
                    val imageUrl = async {  artworkRepository.getPicture(artwork.image_id) }
                    artworkWithImage.add(artwork.copy(image_url = imageUrl.await()))
                }
                _artworkState.value = Result.Success(artworkWithImage)
            } catch (e: Exception) {
                _artworkState.value = Result.Error(e)
            }
        }
    }
}