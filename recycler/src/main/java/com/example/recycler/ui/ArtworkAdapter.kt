package com.example.recycler.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycler.databinding.ArtworkItemBinding
import com.example.recycler.databinding.Item1Binding
import com.example.recycler.databinding.Item2Binding
import com.example.recycler.di.BASE_URL
import com.example.recycler.model.Artwork
import com.example.recycler.model.BaseItem
import com.example.recycler.model.Item1
import com.example.recycler.model.Item2
import com.example.recycler.ui.BaseType.Companion.VIEW_ITEM_1
import com.example.recycler.ui.BaseType.Companion.VIEW_ITEM_2
import com.example.recycler.ui.BaseType.Companion.VIEW_ITEM_3

class ArtworkAdapter : ListAdapter<BaseItem, RecyclerView.ViewHolder>(BaseDiffUtils()) {

    class ViewHolderArtwork(val binding: ArtworkItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(artwork: Artwork) {
            binding.textView.text = artwork.title
            Glide.with(binding.root)
                .load("${BASE_URL}iiif/2/${artwork.image_id}/full/843,/0/default.jpg")
                .centerCrop()
                .into(binding.image)
        }
    }

    class ViewHolderItem1(val binding: Item1Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item1: Item1) {
            binding.item1.text = item1.title
        }
    }

    class ViewHolderItem2(val binding: Item2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item2: Item2) {
            binding.item2.text = item2.title
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Item1 -> VIEW_ITEM_1
            is Item2 -> VIEW_ITEM_2
            is Artwork -> VIEW_ITEM_3
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_ITEM_1 -> ViewHolderItem1(Item1Binding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_ITEM_2 -> ViewHolderItem2(Item2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_ITEM_3 -> ViewHolderArtwork(ArtworkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Please provide a valid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when {
            holder is ViewHolderItem1 && item is Item1 -> {
                holder.bind(item)
            }
            holder is ViewHolderItem2 && item is Item2 -> {
                holder.bind(item)
            }
            holder is ViewHolderArtwork && item is Artwork -> {
                holder.bind(item)
            }
            else -> throw IllegalArgumentException("Invalid ViewHolder type or item")
        }
    }
}

interface BaseType {
    companion object {
        const val VIEW_ITEM_1: Int = 0
        const val VIEW_ITEM_2: Int = 1
        const val VIEW_ITEM_3: Int = 2
    }
}

class BaseDiffUtils : DiffUtil.ItemCallback<BaseItem>() {

    override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
         return when {
             oldItem is Item1 && newItem is Item1 -> oldItem.id == newItem.id
             oldItem is Item2 && newItem is Item2 -> oldItem.id == newItem.id
             oldItem is Artwork && newItem is Artwork -> oldItem.id == newItem.id
             else -> false
         }
    }

    override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
        return when {
            oldItem is Item1 && newItem is Item1 -> oldItem.id == newItem.id && oldItem.title == newItem.title
            oldItem is Item2 && newItem is Item2 -> oldItem.id == newItem.id && oldItem.title == newItem.title
            oldItem is Artwork && newItem is Artwork -> oldItem.id == newItem.id && oldItem.title == newItem.title
            else -> false
        }
    }
}