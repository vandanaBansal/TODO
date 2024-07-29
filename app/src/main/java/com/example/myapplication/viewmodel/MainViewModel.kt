package com.example.myapplication.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.entities.TodoItem
import com.example.myapplication.model.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(private val repository: TodoRepository) : ViewModel() {
    val todos = repository.allTodos

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addTodo(todo: String) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insert(TodoItem(title = todo))
            }
        }

}