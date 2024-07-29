package com.example.myapplication.view

import android.app.Application
import com.example.myapplication.model.database.AppDatabase
import com.example.myapplication.model.repository.TodoRepository



class ToDoApplication:Application(){

    private val dataBase by lazy { AppDatabase.getDatabase(this) }
    val movieRepository by lazy { TodoRepository(dataBase.getTodoDao()) }
}