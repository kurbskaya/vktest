package com.erya.testvk.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erya.testvk.domain.repository.GifRepository

class ViewModelFactory(private val gifRepository: GifRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FirstScreenViewModel::class.java))
            FirstScreenViewModel(gifRepository) as T
        else
            throw IllegalArgumentException("ViewModel $modelClass Not Found")
    }
}