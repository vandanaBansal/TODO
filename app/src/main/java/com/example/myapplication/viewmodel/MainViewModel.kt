package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.TodoItem
import com.example.myapplication.model.data.UiState
import com.example.myapplication.model.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {
    val todos = repository.allTodos

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading(false))
    val state: StateFlow<UiState> = _uiState

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addTodo(todo: String) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if(todo.equals("error",ignoreCase = true)){
                        throw Exception("Failed to add TODO")
                    }else{
                        repository.insert(TodoItem(title = todo))
                        _uiState.value = UiState.Loading(true)
                        longProcess()
                    }
                }
               catch (e:Exception){
                   _uiState.value = UiState.Error("Failed to add TODO")
               }

            }
        }

 private fun longProcess() {
        viewModelScope.launch {
            try {
                delay(3000) // Simulating network delay
                _uiState.value = UiState.Success
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to add TODO")
            }
        }
    }
    }
