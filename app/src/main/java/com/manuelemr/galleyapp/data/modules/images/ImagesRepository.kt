package com.manuelemr.galleyapp.data.modules.images

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepository(private val api: ImagesApi) {

    suspend fun getImages(page: Int) = withContext(Dispatchers.IO) {
        api.getImages(page)
    }

    suspend fun getImage(id: Int) = withContext(Dispatchers.IO) {
        api.getImages(id)
    }
}