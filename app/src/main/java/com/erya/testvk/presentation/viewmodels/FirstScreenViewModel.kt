package com.erya.testvk.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erya.testvk.domain.repository.GifRepository
import com.erya.testvk.presentation.GifMapper
import com.erya.testvk.presentation.models.Gif
import com.erya.testvk.presentation.models.UiItemError
import kotlinx.coroutines.launch

class FirstScreenViewModel(private val repository: GifRepository) : ViewModel() {

    private val _randomGifsLiveData = MutableLiveData<UiItemError<List<Gif>>>()
    val randomGifsLiveData: LiveData<UiItemError<List<Gif>>> = _randomGifsLiveData

    fun getRandomGiphs() {
        viewModelScope.launch {
            runCatching {
                repository.getRandom().map { GifMapper.mapDomainToGif(it) }
            }.onSuccess { savedProducts ->
                _randomGifsLiveData.value = UiItemError.Success(savedProducts)
            }.onFailure { exception ->
                _randomGifsLiveData.value = UiItemError.Error(exception)
            }
        }
    }

    fun getSpecialGif(question: String) {
        viewModelScope.launch {
            runCatching {
                repository.getSpecial(question)?.map { GifMapper.mapDomainToGif(it) }
            }.onSuccess { savedProducts ->
                _randomGifsLiveData.value = UiItemError.Success(savedProducts)
            }.onFailure { exception ->
                _randomGifsLiveData.value = UiItemError.Error(exception)
            }
        }
    }

    fun clearLiveData() {
        _randomGifsLiveData.value = UiItemError.Loading()
    }
}