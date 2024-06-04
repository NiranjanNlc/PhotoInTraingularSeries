package com.example.photointraingularseries.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photointraingularseries.data.ImageInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ImagePickerViewModel : ViewModel() {
    private val _images = MutableStateFlow<List<Uri>>(emptyList())
    val images: StateFlow<List<Uri>> = _images

    private val _sequencedImages = MutableStateFlow<List<ImageInfo>>(emptyList())
    val sequencedImages: StateFlow<List<ImageInfo>> = _sequencedImages

    fun addImage(uri: Uri) {
        viewModelScope.launch {
            val currentList = _images.value.toMutableList()
            if (currentList.size < 2) {
                currentList.add(uri)
                _images.value = currentList
            }
        }
    }

    fun generateSequencedImage(k: Int  ) {
        val imageList = mutableListOf<ImageInfo>()
        val images = _images.value
        Log.i("Images",images.toString())
        for (i in 0 until k) {
            imageList.add(ImageInfo(images[1],i))
        }
        var n = 1
        var trainagularSeq=1
        while(trainagularSeq < k)
        {
            imageList[trainagularSeq]= ImageInfo(images[0],trainagularSeq)
            n++
            trainagularSeq = n*(n+1)/2
        }
        _sequencedImages.value = imageList
    }

    fun clearState() {
        _images.value = emptyList()
        _sequencedImages.value = emptyList()
    }
}
