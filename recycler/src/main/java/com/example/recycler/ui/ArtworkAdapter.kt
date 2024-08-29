package com.example.recycler.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycler.databinding.ArtworkItemBinding
import com.example.recycler.model.Artwork


class ArtworkAdapter : ListAdapter<Artwork, ArtworkAdapter.ViewHolder>(ArtworkDiffUtils()) {

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
        val artwork = getItem(position)
        viewHolder.bind(artwork)
    }
}

class ArtworkDiffUtils : DiffUtil.ItemCallback<Artwork>() {
    override fun areItemsTheSame(oldItem: Artwork, newItem: Artwork): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Artwork, newItem: Artwork): Boolean {
        return oldItem.id == newItem.id && oldItem.title == newItem.title
    }
}