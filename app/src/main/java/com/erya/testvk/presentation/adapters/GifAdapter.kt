package com.erya.testvk.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erya.testvk.databinding.ItemGifBinding
import com.erya.testvk.presentation.models.Gif

class GifAdapter(private val onItemClick:  (item: Gif) -> Unit)
    : ListAdapter<Gif, GifAdapter.GifViewHolder>(GifDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = ItemGifBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GifViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class GifViewHolder(
        gifItemLayoutBinding: ItemGifBinding,
        onItemClick: (item: Gif) -> Unit,
    ) : RecyclerView.ViewHolder(gifItemLayoutBinding.root) {

        private var cell: Gif? = null
        private val binding = gifItemLayoutBinding

        init {
            binding.root.setOnClickListener{
                cell?.let { it1 -> onItemClick(it1) }
            }
        }

        fun bind(product: Gif) {
            this.cell = product
            Glide.with(binding.image.context)
                .asGif()
                .load(product.url)
                .into(binding.image)

        }
    }

}