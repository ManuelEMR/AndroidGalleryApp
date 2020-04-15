package com.manuelemr.galleyapp.presentation.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.manuelemr.galleyapp.data.modules.auth.AuthApi
import com.manuelemr.galleyapp.data.modules.auth.AuthRepository
import com.manuelemr.galleyapp.data.modules.auth.GalleryAuthenticator
import com.manuelemr.galleyapp.data.modules.auth.TokenInterceptor
import com.manuelemr.galleyapp.data.modules.images.ImagesApi
import com.manuelemr.galleyapp.data.modules.images.ImagesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor(get<TokenInterceptor>())
            .authenticator(get<GalleryAuthenticator>())
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://195.39.233.28:8035/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .build()
    }

    single {
        GsonBuilder()
            .create()
    }

    single { TokenInterceptor(get()) }

    single(named("basic")) {
        Retrofit.Builder()
            .baseUrl("http://195.39.233.28:8035/")
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
            )
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .build()
    }

    single {
        // we need "basic" to avoid cyclic dependencies
        GalleryAuthenticator(AuthRepository(get<Retrofit>(named("basic")).create(AuthApi::class.java)), get())
    }

    single {
        get<Retrofit>().create(AuthApi::class.java)
    }

    single {
        AuthRepository(get())
    }

    single {
        get<Retrofit>().create(ImagesApi::class.java)
    }

    single {
        ImagesRepository(get())
    }
}