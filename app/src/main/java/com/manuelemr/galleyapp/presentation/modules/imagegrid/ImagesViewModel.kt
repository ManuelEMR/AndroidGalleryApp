package com.manuelemr.galleyapp.presentation.modules.imagegrid

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.manuelemr.galleyapp.data.modules.images.ImagesRepository
import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import com.manuelemr.galleyapp.domain.ImagesDataFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ImagesViewModel(private val imagesDataFactory: ImagesDataFactory) : ViewModel() {
    val loading: LiveData<Boolean> = imagesDataFactory.dataSource.switchMap { it.loading }
    val images: LiveData<PagedList<ImageData>> by lazy {
        PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(26)
            .setPageSize(26)
            .build()
            .let { LivePagedListBuilder(imagesDataFactory, it) }
            .build()
    }

    fun refresh() {
        images.value?.dataSource?.invalidate()
    }
}
