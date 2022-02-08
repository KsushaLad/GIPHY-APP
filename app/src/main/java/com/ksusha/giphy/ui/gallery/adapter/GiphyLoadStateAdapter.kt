package com.ksusha.giphy.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.ksusha.giphy.databinding.GiphyLoadStateFooterBinding

class GiphyLoadStateAdapter(internal val retry: () -> Unit) :
    LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = LoadStateViewHolder(
        GiphyLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,), retry)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
    }
}