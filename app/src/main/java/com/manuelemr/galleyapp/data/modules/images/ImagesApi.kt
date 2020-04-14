package com.manuelemr.galleyapp.data.modules.images

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImagesApi {

    @GET("images")
    suspend fun getImages(@Query("page") page: Int): Map<String, Any>

    @GET("images/{id}")
    suspend fun getImage(@Path("id") imageId: Int): Map<String, Any>
}