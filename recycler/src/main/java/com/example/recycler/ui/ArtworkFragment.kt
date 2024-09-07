package com.example.recycler.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler.data.network.Result
import com.example.recycler.databinding.FragmentArtworkBinding
import com.example.recycler.model.BaseItem
import com.example.recycler.model.Item1
import com.example.recycler.model.Item2
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtworkFragment : Fragment() {
    private lateinit var binding: FragmentArtworkBinding
    private val artworkViewModel: ArtworkViewModel by viewModel()
    private lateinit var artworkAdapter: ArtworkAdapter
    val list = mutableListOf<BaseItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtworkBinding.inflate(inflater, container, false)
        list.add(Item1(1, "Item 1"))
        list.add(Item2(1, "Item 2"))
        artworkAdapter = ArtworkAdapter()
        artworkAdapter.submitList(list)

        binding.artworkRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.artworkRecyclerview.adapter = artworkAdapter

        list.add(Item1(1, "Item 3"))
        list.add(Item1(1, "Item 4"))
        artworkAdapter.submitList(list)

        artworkViewModel.fetchData()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                artworkViewModel.artworkState.collect { data ->
                    when (data) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                        }

                        is Result.Error -> {
                            Toast.makeText(requireContext(), data.exception.message, Toast.LENGTH_LONG).show()
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                artworkViewModel.artworkWithPirateInfoStateFlow.collect { data ->
                    for (artwork in data) {
                        list.add(artwork)
                    }
                    artworkAdapter.submitList(list)
                }
            }
        }

        return binding.root
    }
}