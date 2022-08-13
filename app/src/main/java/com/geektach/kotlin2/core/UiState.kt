package com.geektach.kotlin2.core

sealed class UiState<T> {
    class Error<T>(val error: String) : UiState<T>()
    class Loading<T>() : UiState<T>()
    class Success<T>(val data: T) : UiState<T>()
}