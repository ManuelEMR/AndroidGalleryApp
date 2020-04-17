package com.manuelemr.galleyapp.presentation.modules.imagedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.manuelemr.galleyapp.data.modules.images.ImagesRepository
import com.manuelemr.galleyapp.data.modules.images.models.ImageData
import kotlinx.coroutines.launch
import retrofit2.HttpException


class ImageDetailViewModel(private val imagesRepository: ImagesRepository) : ViewModel() {
    private val _image = MutableLiveData<ImageData>()
    private val _loading = MutableLiveData<Boolean>()

    val image: LiveData<ImageData> = _image
    val loading: LiveData<Boolean> = _loading

    fun getImageDetail(id: String) {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                imagesRepository.getImage(id)
                    .let { _image.postValue(it) }
            }
            catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }
}
