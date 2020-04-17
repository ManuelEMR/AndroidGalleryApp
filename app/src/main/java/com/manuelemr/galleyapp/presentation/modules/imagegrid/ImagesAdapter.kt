package com.manuelemr.galleyapp.presentation.modules.imagegrid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.manuelemr.galleyapp.R
import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import com.manuelemr.galleyapp.presentation.foundation.extensions.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_grid_image.*

class ImagesAdapter(private val onImageClickListener: (ImageData) -> Unit): PagedListAdapter<ImageData, ImagesViewHolder>(
    diffUtil) {

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<ImageData>() {
            override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.item_grid_image, parent, false)
            .let { ImagesViewHolder(it) }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        getItem(position)?.let { imageData ->
            holder.configure(imageData)
            holder.containerView.setOnClickListener { onImageClickListener(imageData) }
        }
    }
}

class ImagesViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun configure(imageData: ImageData) {
        image.load(imageData.croppedPicture)
    }
}