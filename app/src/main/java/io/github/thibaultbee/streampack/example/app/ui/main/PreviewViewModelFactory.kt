package io.github.thibaultbee.streampack.example.app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.thibaultbee.streampack.example.app.utils.StreamerManager

class PreviewViewModelFactory(private val streamerManager: StreamerManager) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreviewViewModel::class.java)) {
            return PreviewViewModel(streamerManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}