package com.manuelemr.galleyapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.manuelemr.galleyapp.data.modules.images.ImagesRepository
import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImagesDataSource(private val imagesRepository: ImagesRepository):
    PageKeyedDataSource<Int, ImageData>() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean> = _loading

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImageData>
    ) {
        scope.launch {
            _loading.postValue(true)
            try {
                imagesRepository.getImages(1)
                    .let { callback.onResult(it.pictures, null, 2) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            _loading.postValue(false)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ImageData>) {
        _loading.postValue(true)
        scope.launch {
            try {
                imagesRepository.getImages(params.key)
                    .let {
                        val nextPage = if(it.hasMore) params.key + 1 else null
                        callback.onResult(it.pictures, nextPage)
                    }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            _loading.postValue(false)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ImageData>) {
    }
}