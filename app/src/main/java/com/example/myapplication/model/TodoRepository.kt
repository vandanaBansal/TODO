package com.example.myapplication.model

import kotlinx.coroutines.flow.Flow

class TodoRepository (private val todoDao: TodoDao){
    // Data Retrieval
    val allTodos: Flow<List<TodoItem>> = todoDao.getAllTodos()

    // Data Modification Methods
    suspend fun insert(todo: TodoItem) {
        todoDao.insert(todo)
    }

}