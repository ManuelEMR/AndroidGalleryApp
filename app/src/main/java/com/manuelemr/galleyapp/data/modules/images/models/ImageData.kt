package com.manuelemr.galleyapp.data.modules.images.models

import com.google.gson.annotations.SerializedName

data class ImageData(
    val id: String,
    @SerializedName("cropped_picture") val croppedPicture: String,
    val author: String?,
    val camera: String?,
    @SerializedName("full_picture") val fullPicture: String?)