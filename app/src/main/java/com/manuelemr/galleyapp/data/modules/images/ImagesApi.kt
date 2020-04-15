package com.manuelemr.galleyapp.data.modules.images

import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import com.manuelemr.galleyapp.data.modules.images.models.ImagesPage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImagesApi {

    @GET("images")
    suspend fun getImages(@Query("page") page: Int): ImagesPage

    @GET("images/{id}")
    suspend fun getImage(@Path("id") imageId: String): ImageData
}