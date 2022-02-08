package com.ksusha.giphy.models

import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    @SerializedName("data")
    val giphyData: List<GiphyImage>
)