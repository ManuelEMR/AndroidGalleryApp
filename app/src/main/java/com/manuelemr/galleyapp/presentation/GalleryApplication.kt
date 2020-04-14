package com.manuelemr.galleyapp.presentation

import android.app.Application
import com.manuelemr.galleyapp.presentation.di.dataModule
import com.manuelemr.galleyapp.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GalleryApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GalleryApplication)
            modules(listOf(presentationModule, dataModule))
        }
    }
}