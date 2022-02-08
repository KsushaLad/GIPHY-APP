package com.ksusha.giphy.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ksusha.giphy.R
import com.ksusha.giphy.databinding.ItemGiphyImageBinding
import com.ksusha.giphy.models.GiphyImage

class GiphyImageAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<GiphyImage, GiphyImageAdapter.GiphyViewHolder>(
        PhotoComparator()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val binding =
            ItemGiphyImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiphyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class GiphyViewHolder(private val binding: ItemGiphyImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)

                    }
                }
            }
        }

        fun bind(image: GiphyImage) {
            binding.apply {
                Glide.with(itemView)
                    .load(image.images.fixedHeightSmall.url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(image: GiphyImage)
    }
}
