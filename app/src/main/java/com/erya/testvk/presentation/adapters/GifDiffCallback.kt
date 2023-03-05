package com.erya.testvk.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.erya.testvk.presentation.models.Gif

class GifDiffCallback : DiffUtil.ItemCallback<Gif>() {

    override fun areItemsTheSame(oldItem: Gif, newItem: Gif) = oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif) =
        oldItem.url == newItem.url
}