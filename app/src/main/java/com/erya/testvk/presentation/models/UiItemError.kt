package com.erya.testvk.presentation.models

sealed class UiItemError<out T> {
    class Success<R>(val elements: R?) : UiItemError<R>()
    class Error(val exception: Throwable?) : UiItemError<Nothing>()
    class Loading : UiItemError<Nothing>()
}