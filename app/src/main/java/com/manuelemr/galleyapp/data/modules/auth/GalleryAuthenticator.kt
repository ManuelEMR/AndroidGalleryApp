package com.manuelemr.galleyapp.data.modules.auth

import com.manuelemr.galleyapp.presentation.foundation.PreferenceHandler
import kotlinx.coroutines.*
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class GalleryAuthenticator(private val authRepository: AuthRepository,
                           private val preferenceHandler: PreferenceHandler): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? =
        runBlocking {
            authRepository.getToken().token
                .also { preferenceHandler.token = it }
        }.let { response.request.newBuilder()
            .header("Authorization", it)
        }.build()
}