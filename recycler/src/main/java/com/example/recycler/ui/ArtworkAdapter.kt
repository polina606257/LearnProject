package com.example.recycler.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler.databinding.ArtworkItemBinding
import com.example.recycler.model.Artwork

class ArtworkAdapter() :
    RecyclerView.Adapter<ArtworkAdapter.ViewHolder>() {
    private var listArtwork: List<Artwork> = emptyList()

    class ViewHolder(val binding: ArtworkItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(artwork: Artwork) {
            binding.textView.text = artwork.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ArtworkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val artwork = listArtwork[position]
        viewHolder.bind(artwork)
    }

    override fun getItemCount() = listArtwork.size

    fun updateData(newArtworks: List<Artwork>) {
        listArtwork = newArtworks
        notifyDataSetChanged()
    }
}