package com.example.myapplication.view

import android.app.Application
import com.example.myapplication.model.database.AppDatabase
import com.example.myapplication.model.repository.TodoRepository
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ToDoApplication:Application()