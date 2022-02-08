package com.ksusha.giphy.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ksusha.giphy.api.GiphyApi
import com.ksusha.giphy.utils.maxSize
import com.ksusha.giphy.utils.size
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GiphyRepository @Inject constructor(private val giphyApi: GiphyApi) {

    fun getSearchResult(query: String) =
        Pager(
            config = PagingConfig(
                size,
                maxSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {GiphyPagingSource(giphyApi, query)}
        ).liveData
}