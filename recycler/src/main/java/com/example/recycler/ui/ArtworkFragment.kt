package com.example.recycler.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler.data.network.Result
import com.example.recycler.databinding.FragmentArtworkBinding
import com.example.recycler.model.Artwork
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtworkFragment : Fragment() {
    private lateinit var binding: FragmentArtworkBinding
    private val artworkViewModel: ArtworkViewModel by viewModel()
    private lateinit var artworkAdapter: ArtworkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtworkBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                artworkViewModel.artworkState.collect { data ->
                    when(data) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            artworkAdapter.updateData(data.data)
                            binding.progressBar.visibility = View.GONE
                        }
                        is Result.Error -> {
                            Toast.makeText(requireContext(), "Error occured", Toast.LENGTH_LONG).show()
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
        artworkViewModel.fetchData()
        artworkAdapter = ArtworkAdapter()

        binding.artworkRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.artworkRecyclerview.adapter = artworkAdapter

        return binding.root
    }
}