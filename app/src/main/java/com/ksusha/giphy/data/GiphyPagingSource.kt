package com.ksusha.giphy.data

import androidx.paging.PagingSource
import com.ksusha.giphy.api.GiphyApi
import com.ksusha.giphy.models.GiphyImage
import retrofit2.HttpException
import java.io.IOException

private const val GIPHY_START_PAGE_INDEX = 1

class GiphyPagingSource(
    private val giphyApi: GiphyApi,
    private val query: String
) : PagingSource<Int, GiphyImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyImage> {
        val position = params.key ?: GIPHY_START_PAGE_INDEX

        return try {
            val response = giphyApi.searchImages(
                query, startResultPosition = position,
                maxObjectsNumber = params.loadSize)
            val images = response.giphyData

            LoadResult.Page(
                data = images,
                prevKey = if (position == GIPHY_START_PAGE_INDEX) null else position - 1,
                nextKey = if (images.isEmpty()) null else position +1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}