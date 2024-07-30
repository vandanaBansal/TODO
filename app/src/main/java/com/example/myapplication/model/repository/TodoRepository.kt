package com.example.myapplication.model.repository

import com.example.myapplication.model.dao.TodoDao
import com.example.myapplication.model.data.TodoItem
import kotlinx.coroutines.flow.Flow


class TodoRepository (private val todoDao: TodoDao) {

    // Data retrieval
    val allTodos: Flow<List<TodoItem>> = todoDao.getAllTodos()

    // Data insert methods
    suspend fun insert(todo: TodoItem) {
        todoDao.insert(todo)
    }

}