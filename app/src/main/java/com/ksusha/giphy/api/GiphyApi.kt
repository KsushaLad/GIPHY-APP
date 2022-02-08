package com.ksusha.giphy.api

import com.ksusha.giphy.models.GiphyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET(search)
    suspend fun searchImages(
        @Query("q")
        searchQueryPhrase: String = "popular",
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("offset")
        startResultPosition: Int,
        @Query("limit")
        maxObjectsNumber: Int
    ): GiphyResponse

    companion object {
        const val BASE_URL = "https://api.giphy.com/v1/gifs/"
        const val API_KEY = "YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL"
        const val search: String = "search"
    }

}