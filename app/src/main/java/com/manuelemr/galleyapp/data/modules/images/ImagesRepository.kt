package com.manuelemr.galleyapp.data.modules.images

import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import com.manuelemr.galleyapp.data.modules.images.models.ImagesPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepository(private val api: ImagesApi) {

    suspend fun getImages(page: Int): ImagesPage = withContext(Dispatchers.IO) {
        api.getImages(page)
    }

    suspend fun getImage(id: String): ImageData = withContext(Dispatchers.IO) {
        api.getImage(id)
    }
}