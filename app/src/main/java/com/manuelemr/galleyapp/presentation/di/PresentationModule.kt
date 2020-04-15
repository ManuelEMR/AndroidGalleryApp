package com.manuelemr.galleyapp.presentation.di

import android.content.Context
import com.manuelemr.galleyapp.presentation.foundation.PreferenceHandler
import com.manuelemr.galleyapp.presentation.modules.imagedetail.ImageDetailViewModel
import com.manuelemr.galleyapp.presentation.modules.imagegrid.ImagesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single {
        PreferenceHandler(androidContext().getSharedPreferences("galley_app", Context.MODE_PRIVATE))
    }

    viewModel {
        ImagesViewModel(get())
    }

    viewModel {
        ImageDetailViewModel(get())
    }
}