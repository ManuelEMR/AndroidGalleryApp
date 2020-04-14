package com.manuelemr.galleyapp.data.modules.auth

import com.manuelemr.galleyapp.presentation.foundation.PreferenceHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import kotlin.coroutines.CoroutineContext

class GalleryAuthenticator(private val authRepository: AuthRepository,
                           private val preferenceHandler: PreferenceHandler): Authenticator {

    val scope = CoroutineScope(Dispatchers.IO)

    override fun authenticate(route: Route?, response: Response): Request? {

        var token: String
        scope.launch {
            authRepository.getToken().token
                .also {
                    preferenceHandler.token = it
                    token = it
                }
        }
        response.request.newBuilder()
            .header("Authorization", token)
            .build()
    }
}