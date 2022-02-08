package com.ksusha.giphy.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ksusha.giphy.R
import com.ksusha.giphy.databinding.FragmentDetailsBinding


class DetailsFragment: Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        binding.apply {
            val photo = args.photo
            Glide.with(this@DetailsFragment)
                .load(photo.images.original.url)
                .error(R.drawable.ic_error)
                .into(imageViewDetails)
        }
    }
}