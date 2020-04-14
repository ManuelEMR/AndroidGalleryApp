package com.manuelemr.galleyapp.data.modules.auth

import com.manuelemr.galleyapp.data.modules.auth.models.GetTokenRequest
import com.manuelemr.galleyapp.data.modules.auth.models.GetTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth")
    suspend fun getToken(@Body request: GetTokenRequest): GetTokenResponse
}