package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.TodoItem
import com.example.myapplication.model.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TodoRepository) :ViewModel() {
    val todos = repository.allTodos

    // 3. Todo Operations
    fun addTodo(todo: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(TodoItem(title = todo)) }

    fun toggleTodo(todoItem: TodoItem) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todoItem.copy(isDone = !todoItem.isDone)) }

}