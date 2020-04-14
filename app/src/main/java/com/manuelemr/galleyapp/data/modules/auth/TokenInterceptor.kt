package com.manuelemr.galleyapp.data.modules.auth

import com.manuelemr.galleyapp.presentation.foundation.PreferenceHandler
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val preferenceHandler: PreferenceHandler) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${preferenceHandler.token}")
            .build()
            .let { chain.proceed(it) }
}