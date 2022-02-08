package com.ksusha.giphy.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ksusha.giphy.data.GiphyRepository

class GalleryViewModel @ViewModelInject constructor(
    private val giphyRepository: GiphyRepository
): ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val giphyImages = currentQuery.switchMap { queryString ->
        giphyRepository.getSearchResult(queryString).cachedIn(viewModelScope)
    }

    fun searchImages(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "beautiful"
    }
}