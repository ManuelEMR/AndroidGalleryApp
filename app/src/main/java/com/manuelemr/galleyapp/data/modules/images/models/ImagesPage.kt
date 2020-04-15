package com.manuelemr.galleyapp.data.modules.images.models

data class ImagesPage(val page: Int, val pageCount: Int, val hasMore: Boolean, val pictures: List<ImageData>)