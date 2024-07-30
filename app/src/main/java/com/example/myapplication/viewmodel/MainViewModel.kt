package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.SimpleLoadingState
import com.example.myapplication.model.data.TodoItem
import com.example.myapplication.model.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(private val repository: TodoRepository) : ViewModel() {
    val todos = repository.allTodos
    private val _state= MutableStateFlow(SimpleLoadingState())
    val state: StateFlow<SimpleLoadingState> =_state
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addTodo(todo: String) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insert(TodoItem(title = todo))
            }
        }

    fun longProcess() {
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            delay(3000)
            _state.update { it.copy(loading = false) }
        }
    }
}