package com.ksusha.giphy.ui.gallery.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ksusha.giphy.models.GiphyImage

class PhotoComparator : DiffUtil.ItemCallback<GiphyImage>() {

    override fun areItemsTheSame(oldItem: GiphyImage, newItem: GiphyImage) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: GiphyImage, newItem: GiphyImage) =
        oldItem == newItem

}