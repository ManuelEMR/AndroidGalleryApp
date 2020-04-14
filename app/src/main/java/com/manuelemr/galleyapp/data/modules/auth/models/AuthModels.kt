package com.manuelemr.galleyapp.data.modules.auth.models

data class GetTokenRequest(val apiKey: String)
data class GetTokenResponse(val token: String)