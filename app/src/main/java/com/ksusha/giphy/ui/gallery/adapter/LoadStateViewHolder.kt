package com.ksusha.giphy.ui.gallery.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ksusha.giphy.databinding.GiphyLoadStateFooterBinding

class LoadStateViewHolder(private val binding: GiphyLoadStateFooterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    constructor(binding: GiphyLoadStateFooterBinding, retry: () -> Unit) : this(binding)

    val unsplashPhotoLoadStateAdapter: GiphyLoadStateAdapter? = null

    init {
        binding.buttonRetry.setOnClickListener {
            unsplashPhotoLoadStateAdapter?.retry?.invoke()
        }
    }

}