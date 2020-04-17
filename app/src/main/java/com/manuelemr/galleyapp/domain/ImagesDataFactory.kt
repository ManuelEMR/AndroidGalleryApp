package com.manuelemr.galleyapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import org.koin.core.KoinComponent
import org.koin.core.get

class ImagesDataFactory: DataSource.Factory<Int, ImageData>(), KoinComponent {

    private val _dataSource = MutableLiveData<ImagesDataSource>()
    val dataSource: LiveData<ImagesDataSource> = _dataSource

    override fun create(): DataSource<Int, ImageData> {
        val newDataSource = get<ImagesDataSource>()
        _dataSource.postValue(newDataSource)
        return newDataSource
    }
}