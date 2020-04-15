package com.manuelemr.galleyapp.presentation.modules.imagegrid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelemr.galleyapp.data.modules.images.ImagesRepository
import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ImagesViewModel(private val imagesRepository: ImagesRepository) : ViewModel() {

    private val _images = MutableLiveData<List<ImageData>>()
    private val _loading = MutableLiveData<Boolean>()

    val images: LiveData<List<ImageData>> = _images
    val loading: LiveData<Boolean> = _loading

    fun getImages() {
        viewModelScope.launch {
            // TODO: Implement pagination
            _loading.postValue(true)
            try {
                imagesRepository.getImages(0)
                    .let {
                        _images.postValue(it.pictures)
                    }
            } catch(e: HttpException) {
                e.printStackTrace()
            }
            _loading.postValue(false)
        }
    }
}
