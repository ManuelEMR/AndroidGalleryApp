package com.manuelemr.galleyapp.presentation.di

import android.content.Context
import com.manuelemr.galleyapp.presentation.foundation.PreferenceHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val presentationModule = module {
    single {
        PreferenceHandler(androidContext().getSharedPreferences("galley_app", Context.MODE_PRIVATE))
    }
}