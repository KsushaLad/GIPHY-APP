package com.ksusha.giphy.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GiphyImage(
    val id: String,
    val images: Images,
): Parcelable