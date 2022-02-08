package com.ksusha.giphy.ui.gallery

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.ksusha.giphy.R
import com.ksusha.giphy.databinding.FragmentGalleryBinding
import com.ksusha.giphy.enums.QueryForSearch
import com.ksusha.giphy.models.GiphyImage
import com.ksusha.giphy.ui.gallery.adapter.GiphyImageAdapter
import com.ksusha.giphy.ui.gallery.adapter.GiphyLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery), GiphyImageAdapter.OnItemClickListener {

    private val viewModel by viewModels<GalleryViewModel>()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding
    private lateinit var textChangeCountDownJob: Job

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)
        val adapter = GiphyImageAdapter(this)
        bindingGalleryFragment(adapter)
        observe(adapter)
        load(adapter)
        setHasOptionsMenu(true)
        buttonsCategory()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_gallery, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (::textChangeCountDownJob.isInitialized)
                    textChangeCountDownJob.cancel()
                textChangeCountDownJob = lifecycleScope.launch {
                    if (query != null) {
                        viewModel.searchImages(query)
                        searchView.clearFocus()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (::textChangeCountDownJob.isInitialized)
                    textChangeCountDownJob.cancel()
                textChangeCountDownJob = lifecycleScope.launch {
                    if (newText != null) {
                        if (newText.isEmpty()) {
                            viewModel.searchImages("popular")
                            searchView.clearFocus()
                        }
                    }
                }
                return false
            }
        })
    }

    private fun buttonsCategory() {
        binding?.nature?.setOnClickListener {
            binding?.recyclerView?.scrollToPosition(0)
            viewModel.searchImages(QueryForSearch.D3.name)
        }

        binding?.bus?.setOnClickListener {
            binding?.recyclerView?.scrollToPosition(0)
            viewModel.searchImages(QueryForSearch.TEXTURES.name)
        }

        binding?.car?.setOnClickListener {
            binding?.recyclerView?.scrollToPosition(0)
            viewModel.searchImages(QueryForSearch.NATURE.name)
        }

        binding?.train?.setOnClickListener {
            binding?.recyclerView?.scrollToPosition(0)
            viewModel.searchImages(QueryForSearch.FOOD.name)
        }

        binding?.trending?.setOnClickListener {
            binding?.recyclerView?.scrollToPosition(0)
            viewModel.searchImages(QueryForSearch.TRAVEL.name)
        }

        binding?.animals?.setOnClickListener {
            binding?.recyclerView?.scrollToPosition(0)
            viewModel.searchImages(QueryForSearch.ANIMALS.name)
        }
    }

    private fun observe(unsplashPhotoAdapter: GiphyImageAdapter) {
        viewModel.giphyImages.observe(viewLifecycleOwner) {
            unsplashPhotoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun load(unsplashPhotoAdapter: GiphyImageAdapter) {
        unsplashPhotoAdapter.addLoadStateListener { loadState ->
            binding?.apply {
                this.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                val isRefresh = loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        unsplashPhotoAdapter.itemCount < 1
                recyclerView.isVisible = !isRefresh
            }
        }
    }

    private fun bindingGalleryFragment(unsplashPhotoAdapter: GiphyImageAdapter) {
        binding?.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = unsplashPhotoAdapter.withLoadStateHeaderAndFooter(
                header = GiphyLoadStateAdapter { unsplashPhotoAdapter.retry() },
                footer = GiphyLoadStateAdapter { unsplashPhotoAdapter.retry() })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(image: GiphyImage) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(image)
        findNavController().navigate(action)
    }
}