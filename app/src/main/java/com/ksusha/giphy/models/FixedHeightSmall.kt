package com.ksusha.giphy.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FixedHeightSmall(
    val url: String,
): Parcelable