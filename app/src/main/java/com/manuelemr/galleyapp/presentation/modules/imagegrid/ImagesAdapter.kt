package com.manuelemr.galleyapp.presentation.modules.imagegrid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuelemr.galleyapp.R
import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import com.manuelemr.galleyapp.presentation.foundation.extensions.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_grid_image.*

class ImagesAdapter(private val onImageClickListener: (ImageData) -> Unit): RecyclerView.Adapter<ImagesViewHolder>() {

    var items: List<ImageData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.item_grid_image, parent, false)
            .let { ImagesViewHolder(it) }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = items[position]
        holder.configure(item)
        holder.containerView.setOnClickListener { onImageClickListener(item) }
    }
}

class ImagesViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun configure(imageData: ImageData) {
        image.load(imageData.croppedPicture)
    }
}