package com.example.myapplication.model.data


sealed interface UiState {
    data class Loading (val show :Boolean): UiState
    object Success : UiState
    data class Error(val message: String) : UiState
}