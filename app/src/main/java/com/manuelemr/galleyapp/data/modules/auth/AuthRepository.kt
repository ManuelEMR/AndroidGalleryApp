package com.manuelemr.galleyapp.data.modules.auth

import com.manuelemr.galleyapp.data.modules.auth.models.GetTokenRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(private val api: AuthApi) {

    suspend fun getToken() = withContext(Dispatchers.IO) {
        api.getToken(GetTokenRequest("23567b218376f79d9415"))
    }
}