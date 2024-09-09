package com.example.recycler.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recycler.data.network.Result
import com.example.recycler.model.Artwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class ArtworkViewModel(val artworkRepository: ArtworkRepository) : ViewModel() {
    private val _artworkState = MutableStateFlow<Result<List<Artwork>>>(Result.Loading)
    val artworkState: StateFlow<Result<List<Artwork>>>
        get() = _artworkState

    private val _pirateIndexesState = MutableStateFlow(listOf<Int>())
    private val pirateIndexList = mutableListOf<Int>()

    val artworkWithPirateInfoStateFlow: Flow<List<Artwork>> = artworkState.combine(_pirateIndexesState) { artwork, isPirate ->
        if (artwork is Result.Success) {
           artwork.data.mapIndexed { index, artwork ->  artwork.copy(isPirate = isPirate.contains(index))}
        } else {
            emptyList()
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            try {
                val artworkList = artworkRepository.getArtworks(1, 5)
                for ((index, value) in artworkList.withIndex()) {
                    if(index % 2 != 0) {
                        pirateIndexList.add(index)
                    }
                }
                _pirateIndexesState.value = pirateIndexList
                _artworkState.value = Result.Success(artworkList)
            } catch (e: Exception) {
                _artworkState.value = Result.Error(e)
            }
        }
    }
}