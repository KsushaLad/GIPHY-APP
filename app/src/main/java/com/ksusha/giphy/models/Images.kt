package com.ksusha.giphy.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
    @SerializedName("fixed_height_small")
    val fixedHeightSmall: FixedHeightSmall,
    val original: Original,
): Parcelable